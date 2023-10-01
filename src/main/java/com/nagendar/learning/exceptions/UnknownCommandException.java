/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 11:00 am
 */

package com.nagendar.learning.exceptions;

public class UnknownCommandException extends BaseException{
	public UnknownCommandException() {
	}

	public UnknownCommandException(String message) {
		super(message);
	}
}
