package com.nagendar.learning.constants;/*
 * @author: pagidimarri.nagendar
 * @createdAt: 27/09/23 10:22 pm
 */

import java.util.Set;

public interface CommonConstants {
	String WHITESPACE_DELIMITER = " ";
	String PIPE_OPERATOR = "\\|";
	String WC_COMMAND = "nagi_wc";
	String EXIT_COMMAND = "exit";
	String TEMP_FILE_PATH = "resources/temp.txt";
	String NON_WC_COMMAND = "non_wc_command";
	String COMMAND_OPTION_DELIMITER = "-";
	String BYTE_COUNT_OPTION = "c";
	String LINE_COUNT_OPTION = "l";
	String CHARACTER_COUNT_OPTION = "m";
	String WORD_COUNT_OPTION = "w";
	Set<String> WC_COMMAND_ALLOWED_OPTIONS = Set.of(BYTE_COUNT_OPTION,
			LINE_COUNT_OPTION, CHARACTER_COUNT_OPTION, WORD_COUNT_OPTION);
}
