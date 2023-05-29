package uo.mp.lab11.exception;

import uo.mp.util.check.ArgumentChecks;

public class InvalidLineFormatException extends Exception {

	private int line;
	
	public InvalidLineFormatException(int line,String message) {
		super(message);
		ArgumentChecks.isTrue(line>=0);
		this.line=line;
	}

}
