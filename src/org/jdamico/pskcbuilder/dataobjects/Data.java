package org.jdamico.pskcbuilder.dataobjects;

public class Data {
	private Secret secret = null;
	private String timeInterval = null;
	
	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Secret getSecret() {
		return secret;
	}

	public void setSecret(Secret secret) {
		this.secret = secret;
	}

	public Data(Secret secret, String timeInterval) {
		super();
		this.secret = secret;
		this.timeInterval = timeInterval;
	}
	
	
}
