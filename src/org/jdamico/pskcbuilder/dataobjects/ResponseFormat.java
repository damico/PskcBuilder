package org.jdamico.pskcbuilder.dataobjects;

public class ResponseFormat {
	
	private String length = null;
	private String encoding = null;
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public ResponseFormat(String length, String encoding) {
		super();
		this.length = length;
		this.encoding = encoding;
	}
	
	

}
