package com.esd.app.exception;

public class RouteException extends Exception{

	
	public RouteException(String message) {
        super(message);
    }

    public RouteException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
