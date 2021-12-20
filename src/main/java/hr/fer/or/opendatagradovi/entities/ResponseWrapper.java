package hr.fer.or.opendatagradovi.entities;

import java.util.List;

public class ResponseWrapper<T> {
	
	private String status;
	private String message;
	private List<T> response;
	
	
	public ResponseWrapper() {
		
	}
	
	public ResponseWrapper(String status, String message, List<T> response) {
		this.status = status;
		this.message = message;
		this.response = response;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getResponse() {
		return response;
	}

	public void setResponse(List<T> response) {
		this.response = response;
	} 
	
	

}
