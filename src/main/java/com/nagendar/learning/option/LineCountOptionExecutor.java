/*
 * @author: pagidimarri.nagendar
 * @createdAt: 02/10/23 11:30 am
 */

package com.nagendar.learning.option;

import com.nagendar.learning.io.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LineCountOptionExecutor implements OptionExecutor{
	private final Printer printer;

	public LineCountOptionExecutor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void executeOption(String filepath) {
		Path path = Paths.get(filepath);
		try (Stream<String> lineStream = Files.lines(path)) {
			long lineCount = lineStream.count();
			printer.print(String.format("Lines: %s", lineCount));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
