/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:24 am
 */

package com.nagendar.learning.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
	public static boolean checkIfFileExists(String filePath) {
		Path path = Paths.get(filePath);
		Path absolutePath = path.toAbsolutePath();
		System.out.println("absolutePath = " + absolutePath);
		return Files.exists(path);
	}
}
