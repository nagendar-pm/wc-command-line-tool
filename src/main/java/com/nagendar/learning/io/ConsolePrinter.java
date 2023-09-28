/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 12:49 pm
 */

package com.nagendar.learning.io;

public class ConsolePrinter implements Printer{
	@Override
	public void print(String message) {
		System.out.println(message);
	}
}
