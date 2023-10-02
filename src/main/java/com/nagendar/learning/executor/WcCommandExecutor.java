/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:59 am
 */

package com.nagendar.learning.executor;

import com.nagendar.learning.factory.OptionExecutorFactory;
import com.nagendar.learning.io.Printer;
import com.nagendar.learning.models.Command;

import java.util.Set;

import static com.nagendar.learning.constants.CommonConstants.WC_COMMAND_ALLOWED_OPTIONS;

public class WcCommandExecutor implements CommandExecutor{
	private final Printer printer;
	private final OptionExecutorFactory optionExecutorFactory;

	public WcCommandExecutor(Printer printer) {
		this.printer = printer;
		optionExecutorFactory = new OptionExecutorFactory(printer);
	}

	@Override
	public void execute(Command command) {
		Set<String> setOptions = command.getParamsSet();
		Set<String> filepaths = command.getFilepath();
		if (setOptions.isEmpty()) {
			setOptions.addAll(WC_COMMAND_ALLOWED_OPTIONS);
		}
		for (String filepath : filepaths) {
			printer.print(String.format("Executing file: %s", filepath));
			for (String option : setOptions) {
				optionExecutorFactory.getOptionExecutor(option).executeOption(filepath);
			}
		}
	}

}
