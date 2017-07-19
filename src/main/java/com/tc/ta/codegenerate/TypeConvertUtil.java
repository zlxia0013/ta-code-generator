package com.tc.ta.codegenerate;

public class TypeConvertUtil {
	public static String convertJavaTypeToFlexType(String typeName) {
		if ("Integer".equalsIgnoreCase(typeName)) {
			return "int";
		} else if ("String".equalsIgnoreCase(typeName)) {
			return "String";
		} else if ("BigDecimal".equalsIgnoreCase(typeName)) {
			return "Number";
		} else if ("Date".equalsIgnoreCase(typeName)) {
			return "Date";
		}

		throw new RuntimeException("not supported type: " + typeName);
	}

	public static String convertJavaTypeToDbType(String typeName) {
		if ("Integer".equalsIgnoreCase(typeName)) {
			return "Integer";
		} else if ("String".equalsIgnoreCase(typeName)) {
			return "Varchar";
		} else if ("BigDecimal".equalsIgnoreCase(typeName)) {
			return "Decimal";
		} else if ("Date".equalsIgnoreCase(typeName)) {
			return "Datetime";
		}

		throw new RuntimeException("not supported type: " + typeName);
	}
}
