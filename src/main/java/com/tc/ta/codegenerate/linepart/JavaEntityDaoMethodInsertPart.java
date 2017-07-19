package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class JavaEntityDaoMethodInsertPart extends Part {

	public JavaEntityDaoMethodInsertPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		if (PropertyUtil.methodReturnTypeName != null && PropertyUtil.methodReturnTypeName.toLowerCase().startsWith("list")) {
			sbResult.append(linePrefix);
			sbResult.append("@SuppressWarnings(\"unchecked\")");
			sbResult.append(PropertyUtil.getLineSeperator());
		}

		sbResult.append(linePrefix);
		sbResult.append("public ");
		sbResult.append(PropertyUtil.methodReturnTypeName);
		sbResult.append(" ");
		sbResult.append(PropertyUtil.getMethodNameDeCap());
		sbResult.append("(");
		sbResult.append(PropertyUtil.methodParamTypeName);
		sbResult.append(" ");
		sbResult.append(PropertyUtil.methodParamVarName);
		sbResult.append(") {");
		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix);
		sbResult.append("\t");
		sbResult.append("return (");
		sbResult.append(PropertyUtil.methodReturnTypeName);
		sbResult.append(") ");

		sbResult.append("getSqlMapClientTemplate().");

		if (PropertyUtil.methodReturnTypeName != null && PropertyUtil.methodReturnTypeName.toLowerCase().startsWith("list")) {
			sbResult.append("queryForList");
		} else {
			sbResult.append("queryForObject");
		}

		sbResult.append("(getStatementNameWrap(\"");

		sbResult.append(PropertyUtil.methodName);
		sbResult.append("\")");

		if (PropertyUtil.methodParamVarName != null && PropertyUtil.methodParamVarName.trim().length() > 0) {
			sbResult.append(", ");
			sbResult.append(PropertyUtil.methodParamVarName);
		}

		sbResult.append(");");
		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix);
		sbResult.append("}");
		sbResult.append(PropertyUtil.getLineSeperator());

		return sbResult.toString();
	}
}
