package com.nagendar.learning;

/*
 * @author: pagidimarri.nagendar
 * @createdAt: 27/09/23 7:56 pm
 */

import com.nagendar.learning.executor.CommandExecutor;
import com.nagendar.learning.executor.WcCommandExecutor;
import com.nagendar.learning.io.ConsolePrinter;
import com.nagendar.learning.io.Printer;
import com.nagendar.learning.models.Command;
import com.nagendar.learning.service.CommandProcessorService;
import com.nagendar.learning.service.CommandProcessorServiceImpl;
import com.nagendar.learning.validator.CommandValidator;
import com.nagendar.learning.validator.WcCommandValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		Printer printer = new ConsolePrinter();
		CommandValidator commandValidator = new WcCommandValidator();
		CommandExecutor commandExecutor = new WcCommandExecutor(printer);
		CommandProcessorService commandProcessorService =
				new CommandProcessorServiceImpl(commandValidator, commandExecutor);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			final String input = reader.readLine();
			// TODO: add pipe here
			Command command = new Command(input);
			commandProcessorService.processCommand(command);
		}
	}
}