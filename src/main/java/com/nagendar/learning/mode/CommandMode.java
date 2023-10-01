/*
 * @author: pagidimarri.nagendar
 * @createdAt: 01/10/23 11:54 am
 */

package com.nagendar.learning.mode;

import java.io.IOException;

public interface CommandMode {
	void process(String input) throws IOException;
}
