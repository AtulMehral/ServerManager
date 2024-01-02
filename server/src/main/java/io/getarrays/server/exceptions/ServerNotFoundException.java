package io.getarrays.server.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServerNotFoundException extends Exception {

	public ServerNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {		
		return super.getMessage();
	}
	
	

	
}
