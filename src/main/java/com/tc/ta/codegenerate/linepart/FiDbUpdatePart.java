package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FiDbUpdatePart extends Part {

	public FiDbUpdatePart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		
		sbResult.append(PropertyUtil.fieldName).append(" = #").append(PropertyUtil.fieldName).append(":").append(
				TypeConvertUtil.convertJavaTypeToDbType(PropertyUtil.fieldType)).append("#,");

		return sbResult.toString();
	}
}
