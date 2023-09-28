/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:22 am
 */

package com.nagendar.learning.exceptions;

public class BaseException extends RuntimeException{
	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}
}
