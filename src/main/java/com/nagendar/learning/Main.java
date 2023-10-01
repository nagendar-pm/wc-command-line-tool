package com.nagendar.learning;

/*
 * @author: pagidimarri.nagendar
 * @createdAt: 27/09/23 7:56 pm
 */

import com.nagendar.learning.factory.CommandExecutorFactory;
import com.nagendar.learning.factory.CommandValidatorFactory;
import com.nagendar.learning.io.ConsolePrinter;
import com.nagendar.learning.io.FilePrinter;
import com.nagendar.learning.io.Printer;
import com.nagendar.learning.mode.CommandMode;
import com.nagendar.learning.mode.PipedCommandMode;
import com.nagendar.learning.mode.SingleCommandMode;
import com.nagendar.learning.service.CommandProcessorService;
import com.nagendar.learning.service.CommandProcessorServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		Printer consolePrinter = new ConsolePrinter();
		Printer filePrinter = new FilePrinter();
		CommandValidatorFactory commandValidatorFactory = new CommandValidatorFactory();
		CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(consolePrinter, filePrinter);
		CommandProcessorService commandProcessorService =
				new CommandProcessorServiceImpl(commandValidatorFactory, commandExecutorFactory);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		final CommandMode singleCommandMode = new SingleCommandMode(commandProcessorService);
		final CommandMode pipedCommandMode = new PipedCommandMode(commandProcessorService);
		while (true) {
			final String input = reader.readLine();
			if (input.contains("|")) {
				pipedCommandMode.process(input);
			}
			else {
				singleCommandMode.process(input);
			}
		}
	}
}