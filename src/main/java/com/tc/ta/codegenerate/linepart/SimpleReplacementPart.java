package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class SimpleReplacementPart extends Part {

	public SimpleReplacementPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		String result;

		if (KEYWORD_TABLE_NAME.equals(keyword)) {
			result = PropertyUtil.getTableName();

		} else if (KEYWORD_PACKAGE.equals(keyword)) {
			result = getPackage();

		} else if (KEYWORD_ENTITY_BASE_PACKAGE.equals(keyword)) {
			result = PropertyUtil.getEntityBasePackage();

		} else if (KEYWORD_APP_BASE_PACKAGE.equals(keyword)) {
			result = PropertyUtil.getProperty("appBasePackageName");

		} else if (KEYWORD_ENTITY_CLASS_NAME.equals(keyword)) {
			result = PropertyUtil.getEntityClassName();

		} else if (KEYWORD_ENTITY_CLASS_NAME_VAR.equals(keyword)) {
			result = PropertyUtil.getEntityClassNameVar();

		} else if (KEYWORD_ENTITY_CLASS_NAME_UP.equals(keyword)) {
			result = PropertyUtil.getEntityClassName().toUpperCase();

		} else if (KEYWORD_METHOD_NAME_CAP.equals(keyword)) {
			result = PropertyUtil.getMethodNameCap();

		} else if (KEYWORD_METHOD_NAME_UN_CAP.equals(keyword)) {
			result = PropertyUtil.getMethodNameDeCap();

		} else if (KEYWORD_METHOD_PARAM_VAR_NAME.equals(keyword)) {
			result = PropertyUtil.methodParamVarName;

		} else if (KEYWORD_METHOD_PARAM_TYPE_NAME.equals(keyword)) {
			result = PropertyUtil.methodParamTypeName;

			if (file.getName().toLowerCase().endsWith("as") || file.getName().toLowerCase().endsWith("mxml")) {
				if (result.equalsIgnoreCase("Integer")) {
					result = "int";
				} else if (result.toLowerCase().startsWith("list")) {
					result = "ArrayCollection";
				}
			}
		} else if (KEYWORD_METHOD_RETURN_VAR_NAME.equals(keyword)) {
			result = PropertyUtil.methodReturnVarName;

		} else if (KEYWORD_METHOD_RETURN_TYPE_NAME.equals(keyword)) {
			result = PropertyUtil.methodReturnTypeName;

			if (file.getName().toLowerCase().endsWith("as") || file.getName().toLowerCase().endsWith("mxml")) {
				if (result.equalsIgnoreCase("Integer")) {
					result = "int";
				} else if (result.toLowerCase().startsWith("list")) {
					result = "ArrayCollection";
				}
			}
		} else {
			throw new CgRuntimeException("Undefined key word: %%" + keyword + "%%, line:" + lineNum
					+ ", file:" + file.getAbsolutePath());
		}

		return result;

	}

	private String getPackage() {
		String result = PropertyUtil.getProperty("appBasePackageName") + "."
				+ PropertyUtil.getEntityClassName().toLowerCase();

		StringBuilder tmp = new StringBuilder(file.getAbsolutePath());

		tmp.delete(0, PropertyUtil.getProfileFold().length());

		while (tmp.charAt(0) == File.separatorChar) {
			tmp.deleteCharAt(0);
		}

		while (tmp.indexOf(PropertyUtil.NP_FOLD_PREFIX) == 0) {
			tmp.delete(0, tmp.indexOf(PropertyUtil.FILE_SEPERATOR) + 1);
		}

		if (tmp.indexOf(PropertyUtil.FILE_SEPERATOR) >= 0) {
			tmp.delete(tmp.lastIndexOf(PropertyUtil.FILE_SEPERATOR), tmp.length());

			String relativePackage = Util.replaceAll(tmp.toString(), PropertyUtil.FILE_SEPERATOR, ".");

			result = result + "." + relativePackage;
		}

		return result;
	}
}
