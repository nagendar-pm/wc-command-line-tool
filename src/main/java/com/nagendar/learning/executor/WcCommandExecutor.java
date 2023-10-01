/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:59 am
 */

package com.nagendar.learning.executor;

import com.nagendar.learning.exceptions.IllegalArgumentException;
import com.nagendar.learning.io.Printer;
import com.nagendar.learning.models.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class WcCommandExecutor implements CommandExecutor{
	private final Printer printer;

	public WcCommandExecutor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void execute(Command command) {
		List<String> options = command.getParams();
		String commandOption, filePath;
		if (options.size() == 2) {
			commandOption = options.get(0);
			filePath = options.get(1);
		}
		else {
			commandOption = "";
			filePath = options.get(0);
		}
		execute(commandOption, filePath);
	}

	private void execute(String commandOption, String filePath) {
		switch (commandOption) {
			case "-c" -> computeByteCount(filePath);
			case "-l" -> computeLineCount(filePath);
			case "-m" -> computeCharacterCount(filePath);
			case "-w" -> computeWordCount(filePath);
			case "" -> computeEveryCount(filePath);
			default ->
				throw new IllegalArgumentException(
				String.format("Argument can be any one of -c, -l, -m, or -w. Unexpected arg: %s", commandOption));
		}
	}

	private void computeEveryCount(String filePath) {
		computeByteCount(filePath);
		computeLineCount(filePath);
		computeCharacterCount(filePath);
		computeWordCount(filePath);
	}

	private void computeCharacterCount(String filePath) {
		Path path = Paths.get(filePath);
		try {
			long characterCount = Files.readAllLines(path)
					.stream()
					.mapToLong(line -> line.chars().count())
					.sum();
			printer.print(String.format("Characters: %s", characterCount));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void computeWordCount(String filePath) {
		Path path = Paths.get(filePath);
		try (Stream<String> lineStream = Files.lines(path)) {
			long wordCount = lineStream.mapToInt(line -> new StringTokenizer(line).countTokens())
					.sum();
			printer.print(String.format("Words: %s", wordCount));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void computeLineCount(String filePath) {
		Path path = Paths.get(filePath);
		try (Stream<String> lineStream = Files.lines(path)) {
			long lineCount = lineStream.count();
			printer.print(String.format("Lines: %s", lineCount));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void computeByteCount(String filePath) {
		Path path = Paths.get(filePath);
		try {
			long bytes = Files.size(path);
			printer.print(String.format("Bytes: %s", bytes));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
