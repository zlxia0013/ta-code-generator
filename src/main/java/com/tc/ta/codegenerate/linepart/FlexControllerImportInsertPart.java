package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FlexControllerImportInsertPart extends Part {

	public FlexControllerImportInsertPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("import ");
		sbResult.append(PropertyUtil.getEntityBasePackage());
		sbResult.append(".control.");
		sbResult.append(PropertyUtil.getEntityClassName());
		sbResult.append("Controller;");

		return sbResult.toString();
	}
}
