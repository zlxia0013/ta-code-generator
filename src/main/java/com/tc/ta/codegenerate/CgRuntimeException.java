package com.tc.ta.codegenerate;

public class CgRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -859519572330425369L;

	public CgRuntimeException() {
	}

	public CgRuntimeException(String arg0) {
		super(arg0);
	}

	public CgRuntimeException(Throwable arg0) {
		super(arg0);
	}

	public CgRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
