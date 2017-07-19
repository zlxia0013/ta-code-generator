package com.tc.ta.codegenerate;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeGenerator {
	public CodeGenerator() {
	}

	public void startGenerateTable(String tableName) {
		PropertyUtil.setTableName(tableName);
		PropertyUtil.generateType = PropertyUtil.GEN_TYPE_TABLE;

		if (isTableFoldExisting()) {
			throw new CgRuntimeException("Table is already generated. table:" + tableName);
		}

		File profileBaseDir = new File(PropertyUtil.getProfileFold());

		if (!profileBaseDir.exists() || !profileBaseDir.isDirectory()) {
			throw new CgRuntimeException("Profile Base does not exists, or is not a fold. profileBaseDir:" + profileBaseDir);
		}

		generate(profileBaseDir);

//		doModify(PropertyUtil.getProperty("flexControllerFilePath"));
//		doModify(PropertyUtil.getProperty("flexRemotingConfigFilePath"));
//		doModify(PropertyUtil.getProperty("springConfigFilePath"));
//		doModify(PropertyUtil.getProperty("ibatisSqlMapConfigFilePath"));
	}

	public void startGenerateMethod(String tableName, String methodName, String paramTypeName, String paramVarName, String returnTypeName,
			String returnVarName, boolean needJava, boolean needFlex, boolean needDao) {
		String path;

		PropertyUtil.generateType = PropertyUtil.GEN_TYPE_METHOD;

		PropertyUtil.setTableName(tableName);
		PropertyUtil.methodName = methodName;
		PropertyUtil.methodParamTypeName = paramTypeName;
		PropertyUtil.methodParamVarName = paramVarName;
		PropertyUtil.methodReturnTypeName = returnTypeName;
		PropertyUtil.methodReturnVarName = returnVarName;

		if (needFlex) {
			File profileBaseDir = new File(PropertyUtil.getProfileFold());

			if (!profileBaseDir.exists() || !profileBaseDir.isDirectory()) {
				throw new CgRuntimeException("Profile Base does not exists, or is not a fold.");
			}

			generate(profileBaseDir);

			// flex
			String flexBasePath = PropertyUtil.getDestCodeFold() + File.separatorChar + convertBaseJavaPackageToFilePath() + File.separatorChar
					+ PropertyUtil.getEntityClassName().toLowerCase();

			// flex entity controller
			path = flexBasePath + File.separatorChar + "control" + File.separatorChar + PropertyUtil.getEntityClassName() + "Controller.as";

			path = Util.deleteDupFilePathSepChar(path);

			doModify(path);

			// flex entity delegate
			path = flexBasePath + File.separatorChar + "business" + File.separatorChar + PropertyUtil.getEntityClassName() + "Delegate.as";

			path = Util.deleteDupFilePathSepChar(path);

			doModify(path);
		}

		if (needJava) {
			// java
			String javaBasePath = PropertyUtil.getProperty("javaSrcPath") + File.separatorChar + convertBaseJavaPackageToFilePath() + File.separatorChar
					+ PropertyUtil.getEntityClassName().toLowerCase();

			// java entity bo
			path = javaBasePath + File.separatorChar + "bo" + File.separatorChar + PropertyUtil.getEntityClassName() + "Bo.java";

			path = Util.deleteDupFilePathSepChar(path);

			doModify(path);

			// java entity bo imp
			path = javaBasePath + File.separatorChar + "bo" + File.separatorChar + "imp" + File.separatorChar + PropertyUtil.getEntityClassName()
					+ "BoImp.java";

			path = Util.deleteDupFilePathSepChar(path);

			doModify(path);

			if (needDao) {
				// java entity dao
				path = javaBasePath + File.separatorChar + "dao" + File.separatorChar + PropertyUtil.getEntityClassName() + "Dao.java";

				path = Util.deleteDupFilePathSepChar(path);

				doModify(path);

				// db sql map
				path = javaBasePath + File.separatorChar + "pojo" + File.separatorChar + PropertyUtil.getEntityClassName() + "_SqlMap.xml";

				path = Util.deleteDupFilePathSepChar(path);

				doModify(path);
			}
		}
	}

	public void startInsertingField(String tableName, String fieldType, String fieldName) {
		String path;

		PropertyUtil.generateType = PropertyUtil.GEN_TYPE_FIELD_INSERTER;

		PropertyUtil.setTableName(tableName);
		PropertyUtil.fieldType = fieldType;
		PropertyUtil.fieldName = fieldName;

		String javaEntityBasePath = PropertyUtil.getProperty("javaSrcPath") + File.separatorChar + convertBaseJavaPackageToFilePath() + File.separatorChar
				+ PropertyUtil.getEntityClassName().toLowerCase();

		String flexEntityBasePath = PropertyUtil.getProperty("flexSrcPath") + File.separatorChar + convertBaseJavaPackageToFilePath() + File.separatorChar
				+ PropertyUtil.getEntityClassName().toLowerCase();

		// db sql map
		path = javaEntityBasePath + File.separatorChar + "pojo" + File.separatorChar + PropertyUtil.getEntityClassName() + "_SqlMap.xml";

		path = Util.deleteDupFilePathSepChar(path);

		doModify(path);

		// java entity
		path = javaEntityBasePath + File.separatorChar + "pojo" + File.separatorChar + PropertyUtil.getEntityClassName() + ".java";

		path = Util.deleteDupFilePathSepChar(path);

		doModify(path);
	}

	private void generate(File profileBaseDir) {
		if (".svn".equalsIgnoreCase(profileBaseDir.getName())) {
			return;
		}

		if (profileBaseDir.isDirectory()) {
			File[] subFiles = profileBaseDir.listFiles();

			for (int i = 0, len = subFiles.length; i < len; i++) {
				generate(subFiles[i]);
			}
		} else if (profileBaseDir.isFile()) {
			doGenerate(profileBaseDir);
		}
	}

	private void doModify(String filePath) {
		String bfPath = backupFile(filePath);

		File fileSrc = new File(bfPath);
		File fileDest = new File(filePath);

		doGenerate(fileSrc, fileDest);
	}

	private void doGenerate(File profileFile) {
		File fileDest = getProfileDestFile(profileFile);
		doGenerate(profileFile, fileDest);
	}

	private void doGenerate(File fileSrc, File fileDest) {
		System.out.println("from[" + fileSrc.getAbsolutePath() + "]");
		System.out.println("  to[" + fileDest.getAbsolutePath() + "]");
		System.out.println();

		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line;
		int lineNum = 0;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileSrc), "UTF-8"));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDest), "UTF-8"));

			LineParser parser = new LineParser();

			while ((line = reader.readLine()) != null) {
				lineNum++;
				String lineParsed = parser.parse(line, lineNum, fileSrc);
				writer.write(lineParsed);
			}
		} catch (FileNotFoundException e) {
			throw new CgRuntimeException(e);
		} catch (IOException e) {
			throw new CgRuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private File getProfileDestFile(File profileFile) {
		String destFileName = conDestFileName(profileFile);

		File file = new File(destFileName);
		if (file.exists()) {
			throw new CgRuntimeException("目标文件已经存在:" + destFileName);
		}

		Util.createFileIncludeParentFold(destFileName);

		return new File(destFileName);
	}

	private boolean isTableFoldExisting() {
		String destTableFoldPath = PropertyUtil.getDestCodeFold() + File.separatorChar + PropertyUtil.getProperty("destJavaNpPath") + File.separatorChar
				+ convertBaseJavaPackageToFilePath() + PropertyUtil.getEntityClassName().toLowerCase();

		destTableFoldPath = Util.deleteDupFilePathSepChar(destTableFoldPath);

		File file = new File(destTableFoldPath);

		if (file.exists() && file.isDirectory()) {
			return true;
		}

		return false;
	}

	private String conDestFileName(File profileFile) {
		String profilePath = profileFile.getAbsolutePath();

		String relProfilePath = profilePath.substring(PropertyUtil.getProfileFold().length());

		String effectivePath = getEffectivePath(relProfilePath);
		String npPath = getNPPath(relProfilePath, effectivePath);

		npPath = npPath + "main\\java\\";

		effectivePath = parseFileName(effectivePath);

		String destFileName = PropertyUtil.getDestCodeFold() + File.separatorChar + npPath + convertBaseJavaPackageToFilePath()
				+ PropertyUtil.getEntityClassName().toLowerCase() + File.separatorChar + effectivePath;

		return Util.replaceAll(destFileName, PropertyUtil.FILE_SEPERATOR + PropertyUtil.FILE_SEPERATOR, PropertyUtil.FILE_SEPERATOR);
	}

	private String getNPPath(String relProfilePath, String effectivePath) {
		StringBuilder path = new StringBuilder(relProfilePath);

		while (path.charAt(0) == File.separatorChar) {
			path.deleteCharAt(0);
		}

		String npPath = relProfilePath.substring(0, relProfilePath.indexOf(effectivePath));

		return Util.replaceAll(npPath, PropertyUtil.NP_FOLD_PREFIX, "");
	}

	private String getEffectivePath(String relProfilePath) {
		StringBuilder path = new StringBuilder(relProfilePath);

		while (path.charAt(0) == File.separatorChar) {
			path.deleteCharAt(0);
		}

		while (path.indexOf(PropertyUtil.NP_FOLD_PREFIX) == 0) {
			path.delete(0, path.indexOf(PropertyUtil.FILE_SEPERATOR) + 1);
		}

		return path.toString();
	}

	private String convertBaseJavaPackageToFilePath() {
		return Util.replaceAll(PropertyUtil.getProperty("appBasePackageName"), ".", File.separatorChar + "") + PropertyUtil.FILE_SEPERATOR;
	}

	private String parseFileName(String effectivePath) {
		String fileName = effectivePath.substring(effectivePath.lastIndexOf(PropertyUtil.FILE_SEPERATOR) + 1);

		fileName = Util.replaceAll(fileName, PropertyUtil.EN_HOLDER_IN_FILE_NAME, PropertyUtil.getEntityClassName());

		fileName = Util.replaceAll(fileName, PropertyUtil.MN_HOLDER_IN_FILE_NAME, Util.capStr(PropertyUtil.methodName));

		return effectivePath.substring(0, effectivePath.lastIndexOf(PropertyUtil.FILE_SEPERATOR) + 1) + fileName;

	}

	private String backupFile(String filePath) {
		File file = new File(filePath);

		String backupFoldPath = getBackupFoldPath();

		String fileBackupPath = backupFoldPath + PropertyUtil.FILE_SEPERATOR + addDateTimeToFileName(file.getName());

		File fileBackup = new File(fileBackupPath);

		BufferedReader reader = null;
		BufferedWriter writer = null;
		String line;

		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileBackup), "UTF-8"));

			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
		} catch (FileNotFoundException e) {
			throw new CgRuntimeException(e);
		} catch (IOException e) {
			throw new CgRuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return fileBackup.getAbsolutePath();
	}

	private String getBackupFoldPath() {
		String backupFoldPath = PropertyUtil.getProperty("backupFold");

		if (backupFoldPath.indexOf(":\\") < 0) {
			backupFoldPath = PropertyUtil.getDestCodeFold() + File.separatorChar + backupFoldPath;
		}

		backupFoldPath = Util.replaceAll(backupFoldPath, PropertyUtil.FILE_SEPERATOR + PropertyUtil.FILE_SEPERATOR, PropertyUtil.FILE_SEPERATOR);

		Util.createFold(backupFoldPath);

		return backupFoldPath;

	}

	private String addDateTimeToFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");

		String dateTime = format.format(new Date());

		return dateTime + fileName;
	}
}
