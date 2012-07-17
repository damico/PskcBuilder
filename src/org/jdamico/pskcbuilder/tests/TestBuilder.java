package org.jdamico.pskcbuilder.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jdamico.pskcbuilder.dataobjects.Data;
import org.jdamico.pskcbuilder.dataobjects.DeviceInfo;
import org.jdamico.pskcbuilder.dataobjects.Key;
import org.jdamico.pskcbuilder.dataobjects.KeyContainer;
import org.jdamico.pskcbuilder.dataobjects.KeyPackage;
import org.jdamico.pskcbuilder.dataobjects.Secret;
import org.jdamico.pskcbuilder.utils.XmlUtils;
import org.junit.Test;

public class TestBuilder {

	@Test
	public void test() {
		
		String input = "1111111 AF1";
		
		KeyContainer kc = new KeyContainer();
		List<KeyPackage> keyPackageList = new ArrayList<KeyPackage>();
		DeviceInfo di = new DeviceInfo("Feitian", "1234");
		Data d = new Data(new Secret(""), "60");
		Key k= new Key("1", "urn:ietf:params:xml:ns:keyprov:pskc:hotp", "Damico", d);
		keyPackageList.add(new KeyPackage(di, k));
		kc.setKeyPackageList(keyPackageList);
		
		String xmlStr = XmlUtils.getInstance().Obj2XmlStr(kc);
		
	}

}
