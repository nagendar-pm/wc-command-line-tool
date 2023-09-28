package com.nagendar.learning.service;/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 7:41 pm
 */

import com.nagendar.learning.models.Command;

public interface CommandProcessorService {
	void processCommand(Command command);
}
