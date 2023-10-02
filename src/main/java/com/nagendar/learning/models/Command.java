/*
 * @author: pagidimarri.nagendar
 * @createdAt: 27/09/23 8:03 pm
 */

package com.nagendar.learning.models;

import com.nagendar.learning.constants.CommonConstants;
import com.nagendar.learning.exceptions.InvalidNumberOfArgumentsException;

import java.util.*;
import java.util.stream.Collectors;

import static com.nagendar.learning.constants.CommonConstants.*;

public class Command {
	private final String commandStr;
	private String commandName;
	private final String[] rawCommand;
	private List<String> params;
	private Set<String> optionsSet;
	private Set<String> filepaths;

	public Command(String input) {
		this.commandStr = input;
		this.rawCommand = this.commandStr.trim().split(WHITESPACE_DELIMITER);
		if (this.rawCommand.length == 0) {
			throw new InvalidNumberOfArgumentsException("Invalid no of params provided");
		}
		this.commandName = rawCommand[0].toLowerCase();
		this.setCommandName();
	}

	public String getCommandName() {
		return commandName;
	}

	private boolean isTokenized() {
		return Objects.nonNull(this.params) && !this.params.isEmpty();
	}

	private void tokenize() {
		List<String> tokens = Arrays.stream(this.rawCommand)
				.map(String::trim)
				.filter(token -> !token.isEmpty())
				.collect(Collectors.toList());
		if (tokens.isEmpty()) {
			throw new InvalidNumberOfArgumentsException("Invalid no of params provided");
		}
		tokens.remove(0);
		this.params = tokens;
	}

	public List<String> getParams() {
		if (!isTokenized()) tokenize();
		return params;
	}

	public String getCommandStr() {
		return commandStr;
	}

	public void setCommandName() {
		if (!(WC_COMMAND.equals(this.commandName)
				|| EXIT_COMMAND.equals(this.commandName))) {
			this.commandName = NON_WC_COMMAND;
		}
	}

	public void parseParams() {
		setOptionsSet();
		setFilepath();
	}

	public void setOptionsSet() {
		this.optionsSet = this.getParams().stream()
				.map(String::trim)
				.filter(option -> option.startsWith(COMMAND_OPTION_DELIMITER))
				.map(option -> option.substring(1))
				.flatMap(option -> option.chars().mapToObj(c -> String.valueOf((char) c)))
				.collect(Collectors.toCollection(HashSet::new));
	}

	public void setFilepath() {
		List<String> filePathsRaw = this.getParams().stream()
				.map(String::trim)
				.filter(option -> !option.startsWith(COMMAND_OPTION_DELIMITER))
				.toList();
		List<String> filePathsWithSpaces = new ArrayList<>();

		StringBuilder prefix = null;
		for (String filepathRaw : filePathsRaw) {
			if ((filepathRaw.startsWith(DOUBLE_QUOTE_CHARACTER) || filepathRaw.startsWith(SINGLE_QUOTE_CHARACTER))
				&& (filepathRaw.endsWith(DOUBLE_QUOTE_CHARACTER) || filepathRaw.endsWith(SINGLE_QUOTE_CHARACTER))) {
				prefix = new StringBuilder();
				prefix.append(filepathRaw, 1, filepathRaw.length()-1);
				filePathsWithSpaces.add(prefix.toString());
				prefix = null;
			}
			else if (filepathRaw.startsWith(DOUBLE_QUOTE_CHARACTER) || filepathRaw.startsWith(SINGLE_QUOTE_CHARACTER)) {
				prefix = new StringBuilder();
				prefix.append(filepathRaw.substring(1)).append(WHITESPACE_DELIMITER);
			}
			else if ((filepathRaw.endsWith(DOUBLE_QUOTE_CHARACTER) || filepathRaw.endsWith(SINGLE_QUOTE_CHARACTER))
					&& Objects.nonNull(prefix)) {
				prefix.append(filepathRaw, 0, filepathRaw.length()-1);
				filePathsWithSpaces.add(prefix.toString());
				prefix = null;
			}
			else if (Objects.nonNull(prefix)) {
				prefix.append(filepathRaw).append(WHITESPACE_DELIMITER);
			}
			else {
				filePathsWithSpaces.add(filepathRaw);
			}
		}

		this.filepaths = new HashSet<>(filePathsWithSpaces);
	}

	public Set<String> getParamsSet() {
		return this.optionsSet;
	}

	public Set<String> getFilepath() {
		return this.filepaths;
	}
}
