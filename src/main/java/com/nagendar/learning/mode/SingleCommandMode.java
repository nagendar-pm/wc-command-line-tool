/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 11:56 am
 */

package com.nagendar.learning.mode;

import com.nagendar.learning.models.Command;
import com.nagendar.learning.service.CommandProcessorService;

public class SingleCommandMode implements CommandMode {
	private final CommandProcessorService commandProcessorService;

	public SingleCommandMode(CommandProcessorService commandProcessorService) {
		this.commandProcessorService = commandProcessorService;
	}

	@Override
	public void process(String input) {
		Command command = new Command(input);
		commandProcessorService.processCommand(command);
	}
}
