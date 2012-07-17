package org.jdamico.pskcbuilder.dataobjects;

public class Secret {
	private String plainValue = null;

	public String getPlainValue() {
		return plainValue;
	}

	public void setPlainValue(String plainValue) {
		this.plainValue = plainValue;
	}

	public Secret(String plainValue) {
		super();
		this.plainValue = plainValue;
	}
	
	
	
}
