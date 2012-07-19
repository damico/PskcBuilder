package org.jdamico.pskcbuilder.components;

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
import org.jdamico.pskcbuilder.utils.Constants;
import org.jdamico.pskcbuilder.utils.XmlUtils;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * 
 * @author Jose Damico
 * Eclipse Public License - v 1.0 (http://www.eclipse.org/legal/epl-v10.html)
 *
 */
public class Controller {
	
	private static Controller INSTANCE = null;
	public static Controller getInstance(){
		if(INSTANCE == null) INSTANCE = new Controller();
		return INSTANCE;
	}
	private Controller(){}
	
	public int buildPskc(String inputFilePath, String outputFilePath, String manufact, String colSep, String respLen, String counter, String interval) throws Exception{
		
		List<String> lst = Commons.getInstance().getListStringFromFile(inputFilePath);

		KeyContainer kc = new KeyContainer();
		List<KeyPackage> keyPackageList = new ArrayList<KeyPackage>();
		AlgorithmParameters ap = new AlgorithmParameters(new ResponseFormat(respLen, "DECIMAL"));

		for (String string : lst) {

			StringTokenizer st = new StringTokenizer(string, colSep);

			DeviceInfo di = new DeviceInfo(manufact, st.nextToken());
			byte[] byteSeed = Commons.getInstance().hexStringToByteArray(st.nextToken());

			Data d = new Data(new Secret(Base64.encode(byteSeed)), interval, counter);
			Key k= new Key("1", "urn:ietf:params:xml:ns:keyprov:pskc:hotp", manufact, d, ap);
			keyPackageList.add(new KeyPackage(di, k));

		}

		kc.setKeyPackageList(keyPackageList);

		int ret = keyPackageList.size();
		
		String xmlStr = XmlUtils.getInstance().Obj2XmlStr(kc);
		if(XmlUtils.getInstance().isDocValid(xmlStr)) Commons.getInstance().stringToFile(xmlStr, outputFilePath+Constants.DEFAULT_OUTPUT_FILENAME);
		else{
			ret = 0;
			throw new Exception("PSKC validation with XSD failed!");
		}
		return ret;
	}
}
