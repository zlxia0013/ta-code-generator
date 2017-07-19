package com.tc.ta;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FoldComparer {
	private String	oldFoldPath;

	private String	newFoldPath;

	private String	outputFoldPath;

	public FoldComparer(String oldFoldPath, String newFoldPath, String outputFoldPath) {
		checkFoldExists(oldFoldPath);
		checkFoldExists(newFoldPath);

		this.oldFoldPath = oldFoldPath;
		this.newFoldPath = newFoldPath;
		this.outputFoldPath = outputFoldPath;
	}

	public static void main(String[] args) {
		// String oldFoldPath = "C:\\jack\\work\\lp\\app\\LearningPlan";
		// String newFoldPath = "C:\\jack\\work\\lp\\workspace\\LearningPlan";
		// String outputFoldPath = "C:\\jack\\work\\lp\\app\\diff";

		String oldFoldPath = "C:\\jack\\work\\pan\\app\\DoorMatCalc";
		String newFoldPath = "C:\\jack\\work\\pan\\workspace\\DoorMatCalc";
		String outputFoldPath = "C:\\jack\\work\\pan\\app\\diff";

		new FoldComparer(oldFoldPath, newFoldPath, outputFoldPath).start();
	}

	public void start() {
		File outputFold = new File(outputFoldPath);
		deleteFold(outputFold);

		File newFold = new File(newFoldPath);

		compareFold(newFold);
	}

	private void compareFold(File fold) {
		File[] files = fold.listFiles();

		for (File file : files) {
			if (isExcluded(file)) {
				continue;
			}

			if (file.isFile()) {
				compareFile(file);
			} else if (file.isDirectory()) {
				compareFold(file);
			}
		}
	}

	private void compareFile(File newFile) {
		String relPath = newFile.getAbsolutePath().substring(newFoldPath.length());

		String oldFilePath = oldFoldPath + relPath;

		if (!isFileExisting(oldFilePath) || !isIdenticalFile(newFile.getAbsolutePath(), oldFilePath)) {
			String outputFilePath = outputFoldPath + relPath;

			copyFile(newFile.getAbsolutePath(), outputFilePath);
		}
	}

	private String getExtension(File file) {
		String ext = null;

		String fileName = file.getName();

		int lastDotPos = fileName.lastIndexOf(".");

		if (lastDotPos >= 0) {
			ext = fileName.substring(lastDotPos + 1);
		}

		return ext;
	}

	private void deleteFold(File fold) {
		if (!fold.exists()) {
			return;
		}

		File[] files = fold.listFiles();

		for (File file : files) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				deleteFold(file);
			}
		}

		fold.delete();
	}

	private void copyFile(String srcFilePath, String destFilePath) {
		System.out.println(srcFilePath);
		System.out.println("\t==>" + destFilePath);

		createFileIfNotExists(destFilePath);

		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(new File(srcFilePath));
			os = new FileOutputStream(new File(destFilePath));

			byte[] buf = new byte[1024];
			int len;

			while ((len = is.read(buf)) > 0) {
				os.write(buf, 0, len);
			}

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void createFileIfNotExists(String destFilePath) {
		File file = new File(destFilePath);

		if (file.exists()) {
			return;
		}

		createFoldIfNotExists(file.getParentFile());

		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void createFoldIfNotExists(File fold) {
		if (fold.exists()) {
			return;
		}

		createFoldIfNotExists(fold.getParentFile());

		fold.mkdir();
	}

	private boolean isIdenticalFile(String file1, String file2) {
		BufferedReader br1 = null;
		BufferedReader br2 = null;

		String line1, line2;

		try {
			br1 = new BufferedReader(new FileReader(file1));
			br2 = new BufferedReader(new FileReader(file2));

			while (true) {
				line1 = readLine(br1);
				line2 = readLine(br2);

				if (line1 == null) {
					return line2 == null;
				} else {
					if (!line1.equals(line2)) {
						return false;
					}
				}
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br1 != null) {
				try {
					br1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (br2 != null) {
				try {
					br2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String readLine(BufferedReader br) throws IOException {
		String line;

		while ((line = br.readLine()) != null) {
			if (line.trim().length() > 0) {
				return line.trim();
			}
		}

		return null;
	}

	private void checkFoldExists(String foldPath) {
		File oldFold = new File(foldPath);

		if (!oldFold.exists() || !oldFold.isDirectory()) {
			throw new RuntimeException("fold path is not existing: " + foldPath);
		}
	}

	private Boolean isFileExisting(String filePath) {
		File file = new File(filePath);

		if (!file.exists() || !file.isFile()) {
			return false;
		}

		return true;
	}

	// ============================= excluded files
	private static Map<String, Integer>	excludedFiles			= new HashMap<String, Integer>();
	private static Map<String, Integer>	excludedFileExtentions	= new HashMap<String, Integer>();
	private static Map<String, Integer>	excludedFolds			= new HashMap<String, Integer>();

	{
		excludedFiles.put("\\.project", 1);

		excludedFileExtentions.put("class", 1);
		excludedFileExtentions.put("swf", 1);

		excludedFolds.put("\\test", 1);
		excludedFolds.put("\\WebContent\\com", 1);
		excludedFolds.put("\\WebContent\\WEB-INF\\classes", 1);
	}

	private boolean isExcluded(File newFile) {
		if (newFile.isFile()) {
			if (excludedFileExtentions.containsKey(getExtension(newFile))) {
				return true;
			}

			String relPath = newFile.getAbsolutePath().substring(newFoldPath.length());
			if (excludedFiles.containsKey(relPath)) {
				return true;
			}
		} else if (newFile.isDirectory()) {
			String relPath = newFile.getAbsolutePath().substring(newFoldPath.length());
			if (excludedFolds.containsKey(relPath)) {
				return true;
			}
		}

		return false;
	}

	// ============================= end excluded files

	public String getOldFoldPath() {
		return oldFoldPath;
	}

	public void setOldFoldPath(String oldFoldPath) {
		this.oldFoldPath = oldFoldPath;
	}

	public String getNewFoldPath() {
		return newFoldPath;
	}

	public void setNewFoldPath(String newFoldPath) {
		this.newFoldPath = newFoldPath;
	}

	public String getOutputFoldPath() {
		return outputFoldPath;
	}

	public void setOutputFoldPath(String outputFoldPath) {
		this.outputFoldPath = outputFoldPath;
	}
}
