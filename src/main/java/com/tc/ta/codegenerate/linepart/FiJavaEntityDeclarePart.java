package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.PropertyUtil;

import java.io.File;

public class FiJavaEntityDeclarePart extends Part {

	public FiJavaEntityDeclarePart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		
		sbResult.append("private ");
		sbResult.append(PropertyUtil.fieldType);
		sbResult.append(" ");
		sbResult.append(PropertyUtil.fieldName);
		sbResult.append(";");

		return sbResult.toString();
	}
}
