package com.tc.ta.codegenerate;

import java.io.File;
import java.io.IOException;

public class Util {
	public static String replaceAll(String str, String src, String dest) {
		StringBuilder sbResult = new StringBuilder(str);

		int i;
		while ((i = sbResult.indexOf(src)) >= 0) {
			sbResult.replace(i, i + src.length(), dest);
		}

		return sbResult.toString();
	}

	public static String deleteDupFilePathSepChar(String path) {
		return replaceAll(path, PropertyUtil.FILE_SEPERATOR
				+ PropertyUtil.FILE_SEPERATOR, PropertyUtil.FILE_SEPERATOR);
	}

	public static String deCapStr(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}

		StringBuilder sbResult = new StringBuilder(str);

		String firstChar = sbResult.charAt(0) + "";

		sbResult.replace(0, 1, firstChar.toLowerCase());

		return sbResult.toString();

	}

	public static String capStr(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}

		StringBuilder sbResult = new StringBuilder(str);

		String firstChar = sbResult.charAt(0) + "";

		sbResult.replace(0, 1, firstChar.toUpperCase());

		return sbResult.toString();

	}

	public static void createFileIncludeParentFold(String fileName) {
		File file = new File(fileName);
		if (!file.getParentFile().exists()) {
			createFold(file.getParent());
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw new CgRuntimeException("Create file error: " + fileName,
						e);
			}
		}
	}

	public static void createFold(String fileName) {
		File file = new File(fileName);

		if (!file.getParentFile().exists()) {
			createFold(file.getParent());
		}

		if (!file.exists()) {
			file.mkdir();
		}
	}
}
