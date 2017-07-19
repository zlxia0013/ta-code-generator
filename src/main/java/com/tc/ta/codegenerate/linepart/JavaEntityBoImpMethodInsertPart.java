package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class JavaEntityBoImpMethodInsertPart extends Part {

	public JavaEntityBoImpMethodInsertPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("@Override");
		sbResult.append(PropertyUtil.getLineSeperator());

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
		sbResult.append("return getFactDao().");
		sbResult.append(PropertyUtil.getMethodNameDeCap());
		sbResult.append("(");
		sbResult.append(PropertyUtil.methodParamVarName);
		sbResult.append(");");
		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix);
		sbResult.append("}");
		sbResult.append(PropertyUtil.getLineSeperator());

		return sbResult.toString();
	}
}
