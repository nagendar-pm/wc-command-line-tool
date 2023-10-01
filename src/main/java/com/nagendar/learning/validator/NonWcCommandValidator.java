/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 7:19 pm
 */

package com.nagendar.learning.validator;

import com.nagendar.learning.models.Command;

public class NonWcCommandValidator implements CommandValidator{
	@Override
	public boolean validate(Command command) {
		return true;
	}
}
