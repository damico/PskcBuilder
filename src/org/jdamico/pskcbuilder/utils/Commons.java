package org.jdamico.pskcbuilder.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Commons {
	private static Commons INSTANCE = null;
	public static Commons getInstance(){
		if(INSTANCE == null) INSTANCE = new Commons();
		return INSTANCE;
	}
	private Commons(){}
	
	public byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
		return data;
	}
	
	public void stringToFile(String content, String strFilePath){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(strFilePath));
			out.write(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getListStringFromFile(String filePath) {
		
		List<String> ret = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String str;
			while ((str = in.readLine()) != null) ret.add(str);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
