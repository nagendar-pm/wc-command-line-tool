/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 7:44 pm
 */

package com.nagendar.learning.service;

import com.nagendar.learning.executor.CommandExecutor;
import com.nagendar.learning.models.Command;
import com.nagendar.learning.validator.CommandValidator;

public class CommandProcessorServiceImpl implements CommandProcessorService{
	private final CommandValidator commandValidator;
	private final CommandExecutor commandExecutor;

	public CommandProcessorServiceImpl(CommandValidator commandValidator,
	                                   CommandExecutor commandExecutor) {
		this.commandValidator = commandValidator;
		this.commandExecutor = commandExecutor;
	}

	@Override
	public void processCommand(Command command) {
		boolean isValidCommand = commandValidator.validate(command);
		if (isValidCommand) {
			commandExecutor.execute(command);
		}
	}
}
