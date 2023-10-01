/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 10:58 am
 */

package com.nagendar.learning.validator;

import com.nagendar.learning.models.Command;

public class ExitCommandValidator implements CommandValidator{
	@Override
	public boolean validate(Command command) {
		return command.getParams().isEmpty();
	}
}
