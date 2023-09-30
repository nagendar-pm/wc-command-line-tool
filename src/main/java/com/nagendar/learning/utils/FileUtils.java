/*
 * @author: pagidimarri.nagendar
 * @createdAt: 28/09/23 11:24 am
 */

package com.nagendar.learning.utils;

import com.nagendar.learning.constants.CommonConstants;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
	public static boolean checkIfFileExists(String filePath) {
		Path path = Paths.get(filePath);
		return Files.exists(path);
	}

	public static boolean isFile(String filePath) {
		Path path = Paths.get(filePath);
		return Files.isRegularFile(path);
	}

	public static String toAbsolutePath(String maybeRelative) {
		Path path = Paths.get(maybeRelative);
		Path effectivePath = path;
		if (!path.isAbsolute()) {
			Path base = Paths.get("");
			effectivePath = base.resolve(path).toAbsolutePath();
		}
		return effectivePath.normalize().toString();
	}
}
