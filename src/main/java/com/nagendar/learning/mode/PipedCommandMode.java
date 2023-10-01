/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 11:56 am
 */

package com.nagendar.learning.mode;

import com.nagendar.learning.exceptions.UnknownCommandException;
import com.nagendar.learning.models.Command;
import com.nagendar.learning.service.CommandProcessorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.nagendar.learning.constants.CommonConstants.*;

public class PipedCommandMode implements CommandMode{
	private final CommandProcessorService commandProcessorService;

	public PipedCommandMode(CommandProcessorService commandProcessorService) {
		this.commandProcessorService = commandProcessorService;
	}

	@Override
	public void process(String input) throws IOException {
		// 1. List the commands split by the | operator
		// 2. Run whatever commands we get by using runtime process
		// 3. Store the output of other commands in the file and use it with the wc later
		// For now we will think of wc as a terminal command
		LinkedList<String> commands = Arrays.stream(input.split(PIPE_OPERATOR))
				.map(String::trim)
				.collect(Collectors.toCollection(LinkedList::new));

		processNonWcCommands(commands);

		String wcCommand = buildCommandWithFilepath(commands.poll(), false);
		Command command = new Command(wcCommand);
		commandProcessorService.processCommand(command);
	}

	private void processNonWcCommands(LinkedList<String> commands) throws IOException {
		int size = commands.size();
		boolean isFirstCommand = true;

		while (size-- > 1) {
			String command = commands.poll();
			command = buildCommandWithFilepath(command, isFirstCommand);
			assert command != null;
			List<String> outputLines = executeNonWcCommand(command);
			saveLinesToFile(outputLines);
			isFirstCommand = false;
		}
	}

	private List<String> executeNonWcCommand(String command) {
		List<String> outputLines = new LinkedList<>();
		try {
			Process process = Runtime.getRuntime()
					.exec(command.split(WHITESPACE_DELIMITER));
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				outputLines.add(line);
			}
		} catch (IOException e) {
			throw new UnknownCommandException(String.format("Unknown Shell command triggered: %s", command));
		}
		return outputLines;
	}

	private String buildCommandWithFilepath(String command, boolean isFirstCommand) {
		if (isFirstCommand) return command;
		return command + WHITESPACE_DELIMITER + TEMP_FILE_PATH;
	}

	private void saveLinesToFile(List<String> nonWcCommandOutputLines) throws IOException {
		Path path = Paths.get(TEMP_FILE_PATH);
		Files.write(path, nonWcCommandOutputLines, StandardCharsets.UTF_8);
	}

}
