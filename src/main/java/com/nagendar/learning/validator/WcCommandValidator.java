/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:09 am
 */

package com.nagendar.learning.validator;

import com.nagendar.learning.constants.CommonConstants;
import com.nagendar.learning.exceptions.FileDoesNotExistsException;
import com.nagendar.learning.exceptions.IllegalArgumentException;
import com.nagendar.learning.exceptions.InvalidNumberOfArgumentsException;
import com.nagendar.learning.models.Command;
import com.nagendar.learning.utils.FileUtils;

import java.util.List;
import java.util.Set;

public class WcCommandValidator implements CommandValidator{
	private final Set<String> allowedOptions = Set.of("-c", "-l", "-m", "-w");

	@Override
	public boolean validate(Command command) {
		// we are validating everything and if anything
		// fails validation, exception is thrown then and there
		// TODO: where are we validating if `wc` is called correctly?
		List<String> options = command.getParams();
		if (options.size() == 1) {
			validateCommandFilePath(command);
		}
		else if (options.size() == 2) {
			validateCommandOptionsAndFilePath(command);
		}
		else {
			throw new InvalidNumberOfArgumentsException(
					String.format("Expected 1 or 2 args, but found %s", options.size()));
		}
		return true;
	}

	private void validateCommandOptionsAndFilePath(Command command) {
		List<String> options = command.getParams();
		String commandOption = options.get(0);
		String filePath = options.get(1);
		if (!allowedOptions.contains(commandOption)) {
			throw new IllegalArgumentException("Arguments provided in the command are not allowed");
		}
		validateCommandFilePath(filePath);
	}

	private void validateCommandFilePath(Command command) {
		String filePath = command.getParams().get(0);
		validateCommandFilePath(filePath);
	}

	private void validateCommandFilePath(String filePath) {
		filePath = FileUtils.toAbsolutePath(filePath);
		if (!FileUtils.checkIfFileExists(filePath)) {
			throw new FileDoesNotExistsException(String.format("File %s not exists!", filePath));
		}
	}
}
