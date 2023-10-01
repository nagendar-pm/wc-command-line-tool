package com.nagendar.learning.io;/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 12:48 pm
 */

import java.util.List;

public interface Printer {
	default void print(String message) {
	}

	default void print(List<String> message) {
	}
}
