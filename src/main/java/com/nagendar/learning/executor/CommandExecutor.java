package com.nagendar.learning.executor;/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:43 am
 */

import com.nagendar.learning.models.Command;

public interface CommandExecutor {
	void execute(Command command);
}
