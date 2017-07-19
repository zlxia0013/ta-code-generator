package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FiDbInsertIntoValuePart extends Part {

	public FiDbInsertIntoValuePart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		
		sbResult.append("#").append(PropertyUtil.fieldName).append(":").append(TypeConvertUtil.convertJavaTypeToDbType(PropertyUtil.fieldType))
				.append("#,");

		return sbResult.toString();
	}
}
