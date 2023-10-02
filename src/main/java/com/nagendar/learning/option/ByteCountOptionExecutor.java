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

public class ByteCountOptionExecutor implements OptionExecutor{
	private final Printer printer;

	public ByteCountOptionExecutor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void executeOption(String filepath) {
		Path path = Paths.get(filepath);
		try {
			long bytes = Files.size(path);
			printer.print(String.format("Bytes: %s", bytes));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
