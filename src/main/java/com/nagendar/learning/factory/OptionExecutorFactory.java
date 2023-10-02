/*
 * @author: pagidimarri.nagendar
 * @createdAt: 02/10/23 11:08 am
 */

package com.nagendar.learning.factory;

import com.nagendar.learning.constants.CommonConstants;
import com.nagendar.learning.io.Printer;
import com.nagendar.learning.option.*;

import java.util.HashMap;
import java.util.Map;

public class OptionExecutorFactory {
	Map<String, OptionExecutor> optionExecutorMap;

	public OptionExecutorFactory(Printer printer) {
		optionExecutorMap = new HashMap<>();
		optionExecutorMap.put(CommonConstants.BYTE_COUNT_OPTION, new ByteCountOptionExecutor(printer));
		optionExecutorMap.put(CommonConstants.LINE_COUNT_OPTION, new LineCountOptionExecutor(printer));
		optionExecutorMap.put(CommonConstants.CHARACTER_COUNT_OPTION, new CharacterCountOptionExecutor(printer));
		optionExecutorMap.put(CommonConstants.WORD_COUNT_OPTION, new WordCountOptionExecutor(printer));
	}

	public OptionExecutor getOptionExecutor(String option) {
		return optionExecutorMap.get(option);
	}
}
