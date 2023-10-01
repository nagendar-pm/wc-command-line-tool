/*
 * @author: pagidimarri.nagendar
 * @createdAt: 27/09/23 8:03 pm
 */

package com.nagendar.learning.models;

import com.nagendar.learning.constants.CommonConstants;
import com.nagendar.learning.exceptions.InvalidNumberOfArgumentsException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
	private final String commandName;
	private final List<String> params;

	public Command(String input) {
		List<String> tokens = Arrays.stream(input.trim().split(CommonConstants.WHITESPACE_DELIMITER))
				.map(String::trim)
				.filter(token -> !token.isEmpty())
				.collect(Collectors.toList());
		if (tokens.isEmpty()) {
			throw new InvalidNumberOfArgumentsException("Invalid no of params provided");
		}
		this.commandName = tokens.get(0).toLowerCase();
		tokens.remove(0);
		this.params = tokens;
	}

	public String getCommandName() {
		return commandName;
	}

	public List<String> getParams() {
		return params;
	}
}
