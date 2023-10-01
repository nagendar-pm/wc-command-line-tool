/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 7:17 pm
 */

package com.nagendar.learning.executor;

import com.nagendar.learning.exceptions.UnknownCommandException;
import com.nagendar.learning.io.Printer;
import com.nagendar.learning.models.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static com.nagendar.learning.constants.CommonConstants.TEMP_FILE_PATH;
import static com.nagendar.learning.constants.CommonConstants.WHITESPACE_DELIMITER;

public class NonWcCommandExecutor implements CommandExecutor{
	private final Printer printer;

	public NonWcCommandExecutor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void execute(Command command) {
		String commandStr = command.getCommandStr();
		printer.print(String.format("Executing the command `%s`...", commandStr));
		List<String> outputLines = new LinkedList<>();
		try {
			Process process = Runtime.getRuntime()
					.exec(commandStr.split(WHITESPACE_DELIMITER));
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				outputLines.add(line);
			}
			saveLinesToFile(outputLines);
			printer.print(String.format("Output: %s", outputLines));
		} catch (IOException e) {
			throw new UnknownCommandException(String.format("Unknown Shell command triggered: %s", command));
		}
	}

	private void saveLinesToFile(List<String> nonWcCommandOutputLines) throws IOException {
		Path path = Paths.get(TEMP_FILE_PATH);
		Files.write(path, nonWcCommandOutputLines, StandardCharsets.UTF_8);
	}
}
