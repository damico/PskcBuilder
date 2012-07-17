package org.jdamico.pskcbuilder.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdamico.pskcbuilder.dataobjects.KeyContainer;
import org.xml.sax.SAXException;

public class XmlUtils {
	
	private static XmlUtils INSTANCE = null;
	public static XmlUtils getInstance(){
		if(INSTANCE == null) INSTANCE = new XmlUtils();
		return INSTANCE;
	}
	private XmlUtils(){}
	
	public boolean isDocValid(String xml) {

		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		File schemaLocation = new File(Constants.XSD_PATH);

		if(schemaLocation.exists()){
			Schema schema = null;
			try {
				schema = factory.newSchema(schemaLocation);
			} catch (SAXException e) {
				e.printStackTrace();
				//throw new TamandareException("Invalid xsd or xsd not found: "+e.getStackTrace(),this.getClass().getName());
			}
			Validator validator = schema.newValidator();



			Source source = new StreamSource(new StringReader(xml));
			String err = null;
			try {
				validator.validate(source);
			} catch (SAXException e) {
				try {
					err = URLEncoder.encode(e.getMessage(), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				//LoggerManager.getInstance().logAtExceptionTime(this.getClass().getName(), err+" > "+xml);
				//throw new TamandareException(e.getStackTrace(),err);
			} catch (IOException e) {
				try {
					err = URLEncoder.encode(e.getMessage(), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				//throw new TamandareException(e.getStackTrace(),err);
			}
		}else{
			//throw new TamandareException("xsd not found!",this.getClass().getName());
		}






		return true;

	}
	public String Obj2XmlStr(KeyContainer kc) {
		
		//sb.append("\n");
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<KeyContainer Version=\"1.0\" xmlns=\"urn:ietf:params:xml:ns:keyprov:pskc\">\n");
		
		for (int i = 0; i < kc.getKeyPackageList().size(); i++) {
			
			sb.append("<KeyPackage>\n");
			sb.append("<DeviceInfo>\n");
			sb.append("<Manufacturer>"+kc.getKeyPackageList().get(i).getDeviceInfo().getManufacturer()+"</Manufacturer>\n");
			sb.append("<SerialNo>"+kc.getKeyPackageList().get(i).getDeviceInfo().getSerialNo()+"</SerialNo>\n");
			sb.append("</DeviceInfo>\n");
			sb.append("Key Id=\""+i+"\" Algorithm=\""+kc.getKeyPackageList().get(i).getKey().getAlgorithm()+"\">\n");
			sb.append("<Issuer>"+kc.getKeyPackageList().get(i).getKey().getIssuer()+"</Issuer>\n");
			sb.append("<Data>\n" +
					"<TimeInterval>"+kc.getKeyPackageList().get(i).getKey().getData().getTimeInterval()+"<TimeInterval>\n" +
					"<Secret><PlainValue>"+kc.getKeyPackageList().get(i).getKey().getData().getSecret().getPlainValue()+"</PlainValue></Secret></Data></Key>");
			sb.append("</KeyPackage>\n");
		}
		
		sb.append("</KeyContainer>");
		
		   
		
		return sb.toString();
	}

}
