package org.jdamico.pskcbuilder.dataobjects;

public class AlgorithmParameters {
	
	private ResponseFormat responseFormat = null;

	public ResponseFormat getResponseFormat() {
		return responseFormat;
	}

	public void setResponseFormat(ResponseFormat responseFormat) {
		this.responseFormat = responseFormat;
	}

	public AlgorithmParameters(ResponseFormat responseFormat) {
		super();
		this.responseFormat = responseFormat;
	}
	
	

}
