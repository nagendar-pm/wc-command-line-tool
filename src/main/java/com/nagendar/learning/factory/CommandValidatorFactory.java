/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 10:54 am
 */

package com.nagendar.learning.factory;

import com.nagendar.learning.constants.CommonConstants;
import com.nagendar.learning.exceptions.UnknownCommandException;
import com.nagendar.learning.models.Command;
import com.nagendar.learning.validator.CommandValidator;
import com.nagendar.learning.validator.ExitCommandValidator;
import com.nagendar.learning.validator.WcCommandValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandValidatorFactory {
	private final Map<String, CommandValidator> commandValidatorMap;

	public CommandValidatorFactory() {
		this.commandValidatorMap = new HashMap<>();
		commandValidatorMap.put(CommonConstants.WC_COMMAND, new WcCommandValidator());
		commandValidatorMap.put(CommonConstants.EXIT_COMMAND, new ExitCommandValidator());
	}

	public CommandValidator getCommandValidator(Command command) {
		CommandValidator commandValidator = commandValidatorMap.get(command.getCommandName());
		if (Objects.isNull(commandValidator)) {
			throw new UnknownCommandException(String.format("Unknown command found: %s", command.getCommandName()));
		}
		return commandValidator;
	}
}
