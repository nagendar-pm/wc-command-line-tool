package com.nagendar.learning;

/*
 * @author: pagidimarri.nagendar
 * @createdAt: 27/09/23 7:56 pm
 */

import com.nagendar.learning.factory.CommandExecutorFactory;
import com.nagendar.learning.factory.CommandValidatorFactory;
import com.nagendar.learning.io.ConsolePrinter;
import com.nagendar.learning.io.Printer;
import com.nagendar.learning.models.Command;
import com.nagendar.learning.service.CommandProcessorService;
import com.nagendar.learning.service.CommandProcessorServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		Printer printer = new ConsolePrinter();
		CommandValidatorFactory commandValidatorFactory = new CommandValidatorFactory();
		CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(printer);
		CommandProcessorService commandProcessorService =
				new CommandProcessorServiceImpl(commandValidatorFactory, commandExecutorFactory);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			final String input = reader.readLine();
			// TODO: add pipe here
			Command command = new Command(input);
			commandProcessorService.processCommand(command);
		}
	}
}