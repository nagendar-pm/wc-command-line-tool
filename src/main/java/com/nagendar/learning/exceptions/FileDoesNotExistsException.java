/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:22 am
 */

package com.nagendar.learning.exceptions;

public class FileDoesNotExistsException extends BaseException{
	public FileDoesNotExistsException() {
	}

	public FileDoesNotExistsException(String message) {
		super(message);
	}
}
