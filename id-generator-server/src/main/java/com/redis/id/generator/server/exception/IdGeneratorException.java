package com.redis.id.generator.server.exception;



public class IdGeneratorException extends RuntimeException {

	/**
	 */
	private static final long serialVersionUID = 2477146815078768003L;
	
	public IdGeneratorException(){
	}
	
	public IdGeneratorException(String message){
		super(message);
	}
	
	public IdGeneratorException(Throwable throwable){
		super(throwable);
	}
	
	public IdGeneratorException(String message, Throwable throwable){
		super(message, throwable);
	}

}
