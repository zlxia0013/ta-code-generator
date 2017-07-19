package com.tc.ta.codegenerate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	public static String		generateType;													// table,
	// method

	public static String		GEN_TYPE_TABLE						= "TABLE";					// table

	public static String		GEN_TYPE_METHOD						= "METHOD";				// method

	public static String		GEN_TYPE_FIELD_INSERTER				= "FIELD_INSERTER";		// field
	// inserter

	private static Properties	props;

	public static String		tableName;

	public static String		methodName;

	public static String		methodParamTypeName;

	public static String		methodParamVarName;

	public static String		methodReturnTypeName;

	public static String		methodReturnVarName;

	public static String		fieldType;

	public static String		fieldName;

	private static String		entityClassName;

	public static String		FILE_SEPERATOR						= File.separatorChar + "";

	public static String		NP_FOLD_PREFIX						= "_NP_";

	public static String		EN_HOLDER_IN_FILE_NAME				= "_EN_";

	public static String		MN_HOLDER_IN_FILE_NAME				= "_MN_";

	public static String		KEYWORD_SEPERATOR					= "%%";

	public static String		KEYWORD_CONTROL_SP					= "%";

	public static String		KEYWORD_CONTROL_INNER_SP			= ",";

	public static String		KEYWORD_NO_PROCESS_TABLE			= "NPT";

	public static String		KEYWORD_NO_PROCESS_METHOD			= "NPM";

	public static String		KEYWORD_PROCESS_ONLY_FOR_INS_FLD	= "ONLY_FOR_INS_FLD";

	static {
		init();
	}

	private static synchronized void init() {
		if (props == null) {
			try {
				props = new Properties();

				props.load(ClassLoader.getSystemResourceAsStream("./cg.properties"));

			} catch (FileNotFoundException e) {
				throw new CgRuntimeException("No cg.properties found in class path.", e);
			} catch (IOException e) {
				throw new CgRuntimeException(e);
			}
		}
	}

	public static String getProperty(String keyword) {
		String value = props.getProperty(keyword);

		String ph = getPlaceHolder(value);
		while (ph != null) {
			int start = value.indexOf('{');
			int end = value.indexOf("}", start + 2);

			String subValue = getProperty(ph);
			subValue = subValue == null ? "" : subValue;

			value = value.substring(0, start) + subValue + value.substring(end + 1);

			ph = getPlaceHolder(value);
		}

		return value;
	}

	private static String getPlaceHolder(String value) {
		String ph = null;

		if (value != null) {
			int start;
			int end;

			start = value.indexOf('{');

			if (start >= 0) {
				end = value.indexOf("}", start + 2);

				if (end > 0) {
					ph = value.substring(start + 1, end);
				}
			}
		}

		if (ph == null || ph.trim().length() <= 0) {
			ph = null;
		} else {
			ph = ph.trim();
		}

		return ph;
	}

	public static String getLineSeperator() {
		return (String) java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));
	}

	public static String getProfileFold() {
		if (GEN_TYPE_TABLE.equalsIgnoreCase(generateType)) {
			return getProperty("profileFoldTable");
		} else if (GEN_TYPE_METHOD.equalsIgnoreCase(generateType)) {
			return getProperty("profileFoldMethod");
		}

		throw new CgRuntimeException("Unsupported generate type: " + generateType);
	}

	public static String getDestCodeFold() {
		String path;

		if (GEN_TYPE_TABLE.equalsIgnoreCase(generateType)) {
			path = getProperty("projectRoot");
		} else if (GEN_TYPE_METHOD.equalsIgnoreCase(generateType)) {
			path = getProperty("flexSrcPath");
		} else {
			throw new CgRuntimeException("Unsupported generate type: " + generateType);
		}

		return Util.deleteDupFilePathSepChar(path);
	}

	public static String getTableName() {
		return tableName;
	}

	public static void setTableName(String tableName) {
		PropertyUtil.tableName = tableName;

		// parse entity class name
		String sep = getProperty("tableNameSeperator");

		StringBuilder tmp = new StringBuilder("");
		tmp = new StringBuilder().append(sep).append(tableName.substring(getProperty("tablePrefix").length()));

		String capChar;
		int i;
		while ((i = tmp.indexOf(sep)) >= 0) {
			capChar = tmp.substring(i, i + sep.length() + 1).substring(sep.length()).toUpperCase();

			tmp.replace(i, i + sep.length() + 1, capChar);
		}

		entityClassName = tmp.toString();
	}

	public static String getColNameSeperator() {
		return getProperty("colNameSeperator");
	}

	public static String getEntityBasePackage() {
		return getProperty("appBasePackageName") + "." + entityClassName.toLowerCase();
	}

	public static String getEntityClassName() {
		return entityClassName;
	}

	public static String getEntityClassNameVar() {
		return Util.deCapStr(entityClassName);
	}

	public static void setEntityClassName(String entityClassName) {
		PropertyUtil.entityClassName = entityClassName;
	}

	public static String getMethodNameCap() {
		return Util.capStr(methodName);
	}

	public static String getMethodNameDeCap() {
		return Util.deCapStr(methodName);
	}

	public static String getMethodParamTypeNameDeCap() {
		return Util.deCapStr(methodParamTypeName);
	}

	public static String getMethodParamTypeName(String codeType) {
		if (Constant.CODE_TYPE_JAVA.equals(codeType)) {
			return methodParamTypeName;
		} else if (Constant.CODE_TYPE_FLEX.equals(codeType)) {
			if ("Integer".equalsIgnoreCase(methodParamTypeName)) {
				return "int";
			} else if (methodParamTypeName.startsWith("List")) {
				return "ArrayCollection";
			} else if (methodParamTypeName.startsWith("BigDecimal")) {
				return "Number";
			} else {
				return methodParamTypeName;
			}
		}

		throw new CgRuntimeException("Unsupported code type: " + codeType);
	}
}
