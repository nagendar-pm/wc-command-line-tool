/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 11:02 am
 */

package com.nagendar.learning.executor;

import com.nagendar.learning.io.Printer;
import com.nagendar.learning.models.Command;

public class ExitCommandExecutor implements CommandExecutor {
	private final Printer printer;

	public ExitCommandExecutor(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void execute(Command command) {
		printer.print("Shutting down...");
		System.exit(0);
	}
}
