package com.tc.ta.codegenerate.linepart;


import com.tc.ta.codegenerate.CgRuntimeException;
import com.tc.ta.codegenerate.PropertyUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class Part {
	protected String	keyword;

	protected int		lineNum;

	protected File		file;

	public abstract String getResult(String linePrefix);

	// db
	public static String				KEYWORD_DB_FIELD_LIST_INSERT_COLUMNS					= "DB_FIELD_LIST_INSERT_COLUMNS";

	public static String				KEYWORD_DB_FIELD_LIST_INSERT_VALUES						= "DB_FIELD_LIST_INSERT_VALUES";

	public static String				KEYWORD_DB_FIELD_LIST_UPDATE							= "DB_FIELD_LIST_UPDATE";

	public static String				KEYWORD_DB_FIELD_LIST_QUERY								= "DB_FIELD_LIST_QUERY";

	public static String				KEYWORD_DB_METHOD_INSERT								= "DB_METHOD_INSERT";

	// flex
	public static String				KEYWORD_FLEX_FIELD_LIST									= "FLEX_FIELD_LIST";

	public static String				KEYWORD_FLEX_CONTROLLER_IMPORT_INSERT					= "FLEX_CONTROLLER_IMPORT_INSERT";

	public static String				KEYWORD_FLEX_CONTROLLER_NEW_ENTITY_CONTROLLER_INSERT	= "FLEX_CONTROLLER_NEW_ENTITY_CONTROLLER_INSERT";

	public static String				KEYWORD_FLEX_REMOTING_CONFIG_DESTINATION_INSERT			= "FLEX_REMOTING_CONFIG_DESTINATION_INSERT";

	public static String				KEYWORD_FLEX_ENTITY_CONTROLLER_IMPORT_INSERT			= "ENTITY_CONTROLLER_IMPORT_INSERT";

	public static String				KEYWORD_FLEX_ENTITY_CONTROLLER_COMMAND_INSERT			= "ENTITY_CONTROLLER_COMMAND_INSERT";

	public static String				KEYWORD_FLEX_ENTITY_DELEGATE_METHOD_INSERT				= "FLEX_ENTITY_DELEGATE_METHOD_INSERT";

	// java
	public static String				KEYWORD_JAVA_FIELD_LIST									= "JAVA_FIELD_LIST";

	public static String				KEYWORD_JAVA_FIELD_GET_SET								= "JAVA_FIELD_GET_SET";

	public static String				KEYWORD_JAVA_ENTITY_BO_METHOD_INSERT					= "JAVA_ENTITY_BO_METHOD_INSERT";

	public static String				KEYWORD_JAVA_ENTITY_BO_IMP_METHOD_INSERT				= "JAVA_ENTITY_BO_IMP_METHOD_INSERT";

	public static String				KEYWORD_JAVA_ENTITY_DAO_METHOD_INSERT					= "JAVA_ENTITY_DAO_METHOD_INSERT";

	// spring
	public static String				KEYWORD_SPRING_CONFIG_INSERT							= "SPRING_CONFIG_INSERT";

	// ibatis
	public static String				KEYWORD_IBATIS_CONFIG_INSERT							= "IBATIS_CONFIG_INSERT";

	// simple replacement
	public static String				KEYWORD_TABLE_NAME										= "TABLE_NAME";

	public static String				KEYWORD_PACKAGE											= "PACKAGE";

	public static String				KEYWORD_ENTITY_BASE_PACKAGE								= "ENTITY_BASE_PACKAGE";

	public static String				KEYWORD_APP_BASE_PACKAGE								= "APP_BASE_PACKAGE";

	public static String				KEYWORD_ENTITY_CLASS_NAME								= "ENTITY_CLASS_NAME";

	public static String				KEYWORD_ENTITY_CLASS_NAME_VAR							= "ENTITY_CLASS_NAME_VAR";

	public static String				KEYWORD_ENTITY_CLASS_NAME_UP							= "ENTITY_CLASS_NAME_UP";

	// method
	public static String				KEYWORD_METHOD_NAME_CAP									= "METHOD_NAME_CAP";

	public static String				KEYWORD_METHOD_NAME_UN_CAP								= "METHOD_NAME_UN_CAP";

	public static String				KEYWORD_METHOD_PARAM_VAR_NAME							= "METHOD_PARAM_VAR_NAME";

	public static String				KEYWORD_METHOD_PARAM_TYPE_NAME							= "METHOD_PARAM_TYPE_NAME";

	public static String				KEYWORD_METHOD_RETURN_VAR_NAME							= "METHOD_RETURN_VAR_NAME";

	public static String				KEYWORD_METHOD_RETURN_TYPE_NAME							= "METHOD_RETURN_TYPE_NAME";

	// field inserter
	public static String				KEYWORD_FI_DB_SELECT									= "FI_DB_SELECT";

	public static String				KEYWORD_FI_DB_INSERT_INTO_FIELD							= "FI_DB_INSERT_INTO_FIELD";

	public static String				KEYWORD_FI_DB_INSERT_INTO_VALUE							= "FI_DB_INSERT_INTO_VALUE";

	public static String				KEYWORD_FI_DB_UPDATE									= "FI_DB_UPDATE";

	public static String				KEYWORD_FI_FLEX_ENTITY_DECLARE							= "FI_FLEX_ENTITY_DECLARE";

	public static String				KEYWORD_FI_JAVA_ENTITY_DECLARE							= "FI_JAVA_ENTITY_DECLARE";

	public static String				KEYWORD_FI_JAVA_ENTITY_GET_SET							= "FI_JAVA_ENTITY_GET_SET";

	// Insert Holder Symbol
	private static Map<String, String>	insertHolderSymbols										= new HashMap<String, String>();

	static {
		insertHolderSymbols.put(KEYWORD_FLEX_CONTROLLER_IMPORT_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_FLEX_CONTROLLER_NEW_ENTITY_CONTROLLER_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_FLEX_REMOTING_CONFIG_DESTINATION_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_SPRING_CONFIG_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_IBATIS_CONFIG_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_FLEX_ENTITY_CONTROLLER_IMPORT_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_FLEX_ENTITY_CONTROLLER_COMMAND_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_FLEX_ENTITY_DELEGATE_METHOD_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_JAVA_ENTITY_BO_METHOD_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_JAVA_ENTITY_BO_IMP_METHOD_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_JAVA_ENTITY_DAO_METHOD_INSERT, "Y");
		insertHolderSymbols.put(KEYWORD_DB_METHOD_INSERT, "Y");

		insertHolderSymbols.put(KEYWORD_FI_DB_SELECT, "Y");
		insertHolderSymbols.put(KEYWORD_FI_DB_INSERT_INTO_FIELD, "Y");
		insertHolderSymbols.put(KEYWORD_FI_DB_INSERT_INTO_VALUE, "Y");
		insertHolderSymbols.put(KEYWORD_FI_DB_UPDATE, "Y");

		insertHolderSymbols.put(KEYWORD_FI_FLEX_ENTITY_DECLARE, "Y");
		insertHolderSymbols.put(KEYWORD_FI_JAVA_ENTITY_DECLARE, "Y");
		insertHolderSymbols.put(KEYWORD_FI_JAVA_ENTITY_GET_SET, "Y");
	}

	public Part(String keyword, int lineNum, File file) {
		this.keyword = keyword;
		this.lineNum = lineNum;
		this.file = file;
	}

	public static Part getPart(String keyword, int lineNum, File file) {
		if (keyword.startsWith(PropertyUtil.KEYWORD_SEPERATOR) && keyword.endsWith(PropertyUtil.KEYWORD_SEPERATOR)) {
			String realKeyword = extractRealKeyword(keyword);

			if (realKeyword == null || realKeyword.trim().length() == 0) {
				throw new CgRuntimeException("keyword can't be empty.");
			}

			realKeyword = realKeyword.trim();

			// db
			if (KEYWORD_DB_FIELD_LIST_INSERT_COLUMNS.equalsIgnoreCase(realKeyword)) {
				return new DbFieldListInsertColumnsPart(realKeyword, lineNum, file);

			} else if (KEYWORD_DB_FIELD_LIST_INSERT_VALUES.equalsIgnoreCase(realKeyword)) {
				return new DbFieldListInsertValuesPart(realKeyword, lineNum, file);

			} else if (KEYWORD_DB_FIELD_LIST_UPDATE.equalsIgnoreCase(realKeyword)) {
				return new DbFieldListUpdatePart(realKeyword, lineNum, file);

			} else if (KEYWORD_DB_FIELD_LIST_QUERY.equalsIgnoreCase(realKeyword)) {
				return new DbFieldListQueryPart(realKeyword, lineNum, file);

			} else if (KEYWORD_DB_METHOD_INSERT.equalsIgnoreCase(realKeyword)) {
				return new DbMethodInsertPart(realKeyword, lineNum, file);
			}

			// flex
			if (KEYWORD_FLEX_FIELD_LIST.equalsIgnoreCase(realKeyword)) {
				return new FlexFieldListPart(realKeyword, lineNum, file);

			} else if (KEYWORD_FLEX_CONTROLLER_IMPORT_INSERT.equalsIgnoreCase(realKeyword)) {
				return new FlexControllerImportInsertPart(realKeyword, lineNum, file);

			} else if (KEYWORD_FLEX_CONTROLLER_NEW_ENTITY_CONTROLLER_INSERT.equalsIgnoreCase(realKeyword)) {
				return new FlexControllerNewEntityControllerInsertPart(realKeyword, lineNum, file);

			} else if (KEYWORD_FLEX_REMOTING_CONFIG_DESTINATION_INSERT.equalsIgnoreCase(realKeyword)) {
				return new FlexRemotingConfigDestinationInsertPart(realKeyword, lineNum, file);

			} else if (KEYWORD_FLEX_ENTITY_CONTROLLER_IMPORT_INSERT.equalsIgnoreCase(realKeyword)) {
				return new FlexEntityControllerImportInsertPart(realKeyword, lineNum, file);

			} else if (KEYWORD_FLEX_ENTITY_CONTROLLER_COMMAND_INSERT.equalsIgnoreCase(realKeyword)) {
				return new FlexEntityControllerCommandInsertPart(realKeyword, lineNum, file);

			} else if (KEYWORD_FLEX_ENTITY_DELEGATE_METHOD_INSERT.equalsIgnoreCase(realKeyword)) {
				return new FlexEntityDelegateMethodInsertPart(realKeyword, lineNum, file);
			}

			// java
			if (KEYWORD_JAVA_FIELD_LIST.equalsIgnoreCase(realKeyword)) {
				return new JavaFieldListPart(realKeyword, lineNum, file);

			} else if (KEYWORD_JAVA_FIELD_GET_SET.equalsIgnoreCase(realKeyword)) {
				return new JavaFieldGetSetPart(realKeyword, lineNum, file);

			} else if (KEYWORD_JAVA_ENTITY_BO_METHOD_INSERT.equalsIgnoreCase(realKeyword)) {
				return new JavaEntityBoMethodInsertPart(realKeyword, lineNum, file);

			} else if (KEYWORD_JAVA_ENTITY_BO_IMP_METHOD_INSERT.equalsIgnoreCase(realKeyword)) {
				return new JavaEntityBoImpMethodInsertPart(realKeyword, lineNum, file);

			} else if (KEYWORD_JAVA_ENTITY_DAO_METHOD_INSERT.equalsIgnoreCase(realKeyword)) {
				return new JavaEntityDaoMethodInsertPart(realKeyword, lineNum, file);
			}

			// spring
			if (KEYWORD_SPRING_CONFIG_INSERT.equalsIgnoreCase(realKeyword)) {
				return new SpringConfigInsertPart(realKeyword, lineNum, file);
			}

			// ibatis
			if (KEYWORD_IBATIS_CONFIG_INSERT.equalsIgnoreCase(realKeyword)) {
				return new IbatisConfigInsertPart(realKeyword, lineNum, file);
			}

			// field inserter
			if (KEYWORD_FI_DB_SELECT.equalsIgnoreCase(realKeyword)) {
				return new FiDbSelectPart(realKeyword, lineNum, file);
			} else if (KEYWORD_FI_DB_INSERT_INTO_FIELD.equalsIgnoreCase(realKeyword)) {
				return new FiDbInsertIntoFieldPart(realKeyword, lineNum, file);
			} else if (KEYWORD_FI_DB_INSERT_INTO_VALUE.equalsIgnoreCase(realKeyword)) {
				return new FiDbInsertIntoValuePart(realKeyword, lineNum, file);
			} else if (KEYWORD_FI_DB_UPDATE.equalsIgnoreCase(realKeyword)) {
				return new FiDbUpdatePart(realKeyword, lineNum, file);
			} else if (KEYWORD_FI_FLEX_ENTITY_DECLARE.equalsIgnoreCase(realKeyword)) {
				return new FiFlexEntityDeclarePart(realKeyword, lineNum, file);
			} else if (KEYWORD_FI_JAVA_ENTITY_DECLARE.equalsIgnoreCase(realKeyword)) {
				return new FiJavaEntityDeclarePart(realKeyword, lineNum, file);
			}else if (KEYWORD_FI_JAVA_ENTITY_GET_SET.equalsIgnoreCase(realKeyword)) {
				return new FiJavaEntityGetSetPart(realKeyword, lineNum, file);
			}

			return new SimpleReplacementPart(realKeyword, lineNum, file);
		} else {
			return new PlainTextPart(keyword, lineNum, file);
		}
	}

	private static String extractRealKeyword(String keyword) {
		String keywordEs = keyword.substring(PropertyUtil.KEYWORD_SEPERATOR.length(), keyword.lastIndexOf(PropertyUtil.KEYWORD_SEPERATOR));

		if (keywordEs.indexOf(PropertyUtil.KEYWORD_CONTROL_SP) <= 0) {
			return keywordEs;
		}

		return keywordEs.substring(keywordEs.indexOf(PropertyUtil.KEYWORD_CONTROL_SP) + 1);
	}

	public boolean isInsertHolderSymbol() {
		return insertHolderSymbols.get(keyword) != null;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
