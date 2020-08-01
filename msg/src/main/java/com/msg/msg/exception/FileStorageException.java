package com.msg.msg.exception;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = -1424427894395902172L;

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
