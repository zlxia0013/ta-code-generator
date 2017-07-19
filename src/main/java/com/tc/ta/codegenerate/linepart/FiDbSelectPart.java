package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FiDbSelectPart extends Part {

	public FiDbSelectPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		
		sbResult.append("a.").append(PropertyUtil.fieldName).append(" as ").append(PropertyUtil.fieldName).append(",");

		return sbResult.toString();
	}
}
