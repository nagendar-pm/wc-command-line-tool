/*
 * @author: pagidimarri.nagendar
 * @createdAt: 02/10/23 11:33 am
 */

package com.nagendar.learning.option;

import com.nagendar.learning.io.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class WordCountOptionExecutor implements OptionExecutor{
	private final Printer printer;

	public WordCountOptionExecutor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void executeOption(String filepath) {
		Path path = Paths.get(filepath);
		try (Stream<String> lineStream = Files.lines(path)) {
			long wordCount = lineStream.mapToInt(line -> new StringTokenizer(line).countTokens())
					.sum();
			printer.print(String.format("Words: %s", wordCount));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
