package org.jdamico.pskcbuilder.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jdamico.pskcbuilder.dataobjects.AlgorithmParameters;
import org.jdamico.pskcbuilder.dataobjects.Data;
import org.jdamico.pskcbuilder.dataobjects.DeviceInfo;
import org.jdamico.pskcbuilder.dataobjects.Key;
import org.jdamico.pskcbuilder.dataobjects.KeyContainer;
import org.jdamico.pskcbuilder.dataobjects.KeyPackage;
import org.jdamico.pskcbuilder.dataobjects.ResponseFormat;
import org.jdamico.pskcbuilder.dataobjects.Secret;
import org.jdamico.pskcbuilder.utils.Commons;
import org.jdamico.pskcbuilder.utils.XmlUtils;
import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * 
 * @author Jose Damico
 * Eclipse Public License - v 1.0 (http://www.eclipse.org/legal/epl-v10.html)
 *
 */
public class TestBuilder {

	private String input = null;
	
	@Before
	public void setUp() throws Exception {
		
		input =  	"NFDM00000001;94D690C0efefcbcbff33C7E810CD22A367B3D32B\n" +
					"NFDM00000002;efefcb94D690C0cbff33C7E810CD22A367B3D32B";
		
	}
	
	@Test
	public void test() {

		

		Commons.getInstance().stringToFile(input, "/tmp/testInput.txt");
		List<String> lst = Commons.getInstance().getListStringFromFile("/tmp/testInput.txt");

		KeyContainer kc = new KeyContainer();
		List<KeyPackage> keyPackageList = new ArrayList<KeyPackage>();
		AlgorithmParameters ap = new AlgorithmParameters(new ResponseFormat("6", "DECIMAL"));

		for (String string : lst) {

			StringTokenizer st = new StringTokenizer(string, ";");

			DeviceInfo di = new DeviceInfo("xyzw", st.nextToken());
			byte[] byteSeed = Commons.getInstance().hexStringToByteArray(st.nextToken());

			Data d = new Data(new Secret(Base64.encode(byteSeed)), "60", "0");
			Key k= new Key("1", "urn:ietf:params:xml:ns:keyprov:pskc:hotp", "xyzw", d, ap);
			keyPackageList.add(new KeyPackage(di, k));

		}

		kc.setKeyPackageList(keyPackageList);

		String xmlStr = XmlUtils.getInstance().Obj2XmlStr(kc);
		if(XmlUtils.getInstance().isDocValid(xmlStr)) Commons.getInstance().stringToFile(xmlStr, "/tmp/testInput.xml");

	}

}
