/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 7:44 pm
 */

package com.nagendar.learning.service;

import com.nagendar.learning.factory.CommandExecutorFactory;
import com.nagendar.learning.factory.CommandValidatorFactory;
import com.nagendar.learning.models.Command;

public class CommandProcessorServiceImpl implements CommandProcessorService{
	private final CommandValidatorFactory commandValidatorFactory;
	private final CommandExecutorFactory commandExecutorFactory;

	public CommandProcessorServiceImpl(CommandValidatorFactory commandValidatorFactory,
	                                   CommandExecutorFactory commandExecutorFactory) {
		this.commandValidatorFactory = commandValidatorFactory;
		this.commandExecutorFactory = commandExecutorFactory;
	}

	@Override
	public void processCommand(Command command) {
		boolean isValidCommand = commandValidatorFactory.getCommandValidator(command)
				.validate(command);
		if (isValidCommand) {
			commandExecutorFactory.getCommandExecutor(command)
					.execute(command);
		}
	}
}
