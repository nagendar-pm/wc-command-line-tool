/*
 * @author: pagidimarri.nagendar
 * @createdAt: 02/10/23 11:31 am
 */

package com.nagendar.learning.option;

import com.nagendar.learning.io.Printer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CharacterCountOptionExecutor implements OptionExecutor{
	private final Printer printer;

	public CharacterCountOptionExecutor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void executeOption(String filepath) {
		Path path = Paths.get(filepath);
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
}
