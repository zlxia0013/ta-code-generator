package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class JavaEntityBoMethodInsertPart extends Part {

	public JavaEntityBoMethodInsertPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("public ");
		sbResult.append(PropertyUtil.methodReturnTypeName);
		sbResult.append(" ");
		sbResult.append(PropertyUtil.getMethodNameDeCap());
		sbResult.append("(");
		sbResult.append(PropertyUtil.methodParamTypeName);
		sbResult.append(" ");
		sbResult.append(PropertyUtil.methodParamVarName);
		sbResult.append(");");

		sbResult.append(PropertyUtil.getLineSeperator());

		return sbResult.toString();
	}
}
