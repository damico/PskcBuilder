package org.jdamico.pskcbuilder.dataobjects;

/**
 * 
 * @author Jose Damico
 * Eclipse Public License - v 1.0 (http://www.eclipse.org/legal/epl-v10.html)
 *
 */
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
