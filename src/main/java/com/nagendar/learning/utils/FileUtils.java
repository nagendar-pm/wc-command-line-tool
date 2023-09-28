/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:24 am
 */

package com.nagendar.learning.utils;

import java.io.File;

public class FileUtils {
	public static boolean checkIfFileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
}
