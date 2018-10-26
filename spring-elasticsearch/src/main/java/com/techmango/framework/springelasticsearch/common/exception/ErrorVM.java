package com.techmango.framework.springelasticsearch.common.exception;


import java.io.Serializable;

public class ErrorVM implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String description;
    private final int errorCode;

    public ErrorVM(String message) {
        this(message, null, 0);
    }

    public ErrorVM(String message, String description, int errorCode) {
        this.message = message;
        this.description = description;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

	public int getErrorCode() {
		return errorCode;
	}

}
