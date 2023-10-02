/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:09 am
 */

package com.nagendar.learning.validator;

import com.nagendar.learning.exceptions.FileDoesNotExistsException;
import com.nagendar.learning.exceptions.IllegalArgumentException;
import com.nagendar.learning.models.Command;
import com.nagendar.learning.utils.FileUtils;

import java.util.Set;

import static com.nagendar.learning.constants.CommonConstants.WC_COMMAND_ALLOWED_OPTIONS;

public class WcCommandValidator implements CommandValidator{

	@Override
	public boolean validate(Command command) {
		validateCommandOptionsAndFilePath(command);
		return true;
	}

	private void validateCommandOptionsAndFilePath(Command command) {
		command.parseParams();
		Set<String> options = command.getParamsSet();
		Set<String> filePaths = command.getFilepath();
		for (String commandOption : options) {
			if (!WC_COMMAND_ALLOWED_OPTIONS.contains(commandOption)) {
				throw new IllegalArgumentException(
						String.format("Arguments provided in the command are not allowed: -%s", commandOption));
			}
		}
		for (String filePath : filePaths) {
			validateCommandFilePath(filePath);
		}
	}

	private void validateCommandFilePath(String filePath) {
		filePath = FileUtils.toAbsolutePath(filePath);
		if (!FileUtils.checkIfFileExists(filePath)) {
			throw new FileDoesNotExistsException(String.format("File %s not exists!", filePath));
		}
		if (!FileUtils.isFile(filePath)) {
			throw new FileDoesNotExistsException(String.format("Provided path is not a file: %s", filePath));
		}
	}
}
