package com.nagendar.learning.validator;
/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:02 am
 */

import com.nagendar.learning.models.Command;

public interface CommandValidator {
	boolean validate(Command command);
}
