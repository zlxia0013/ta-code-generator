package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FiFlexEntityDeclarePart extends Part {

	public FiFlexEntityDeclarePart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		
		sbResult.append("public var ");
		sbResult.append(PropertyUtil.fieldName);
		sbResult.append(":");
		sbResult.append(TypeConvertUtil.convertJavaTypeToFlexType(PropertyUtil.fieldType));
		sbResult.append(";");

		return sbResult.toString();
	}
}
