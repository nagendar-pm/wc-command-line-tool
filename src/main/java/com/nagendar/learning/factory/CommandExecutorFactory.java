/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 11:03 am
 */

package com.nagendar.learning.factory;

import com.nagendar.learning.constants.CommonConstants;
import com.nagendar.learning.exceptions.UnknownCommandException;
import com.nagendar.learning.executor.CommandExecutor;
import com.nagendar.learning.executor.ExitCommandExecutor;
import com.nagendar.learning.executor.WcCommandExecutor;
import com.nagendar.learning.io.Printer;
import com.nagendar.learning.models.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandExecutorFactory {
	Map<String, CommandExecutor> commandExecutorMap;

	public CommandExecutorFactory(Printer printer) {
		this.commandExecutorMap = new HashMap<>();
		commandExecutorMap.put(CommonConstants.WC_COMMAND, new WcCommandExecutor(printer));
		commandExecutorMap.put(CommonConstants.EXIT_COMMAND, new ExitCommandExecutor(printer));
	}

	public CommandExecutor getCommandExecutor(Command command) {
		CommandExecutor commandExecutor = commandExecutorMap.get(command.getCommandName());
		if (Objects.isNull(commandExecutor)) {
			throw new UnknownCommandException(String.format("Unknown command found: %s", command.getCommandName()));
		}
		return commandExecutor;
	}
}
