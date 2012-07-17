package org.jdamico.pskcbuilder.dataobjects;

public class Key {

	
	private String id = null;
	private String algorithm = null;
	private String issuer = null;
	private Data data = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
	public Key(String id, String algorithm, String issuer, Data data) {
		super();
		this.id = id;
		this.algorithm = algorithm;
		this.issuer = issuer;
		this.data = data;
	}
	
	
}
