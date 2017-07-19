package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.PropertyUtil;

import java.io.File;

public class FlexEntityControllerImportInsertPart extends Part {

	public FlexEntityControllerImportInsertPart(String keyword, int lineNum,
			File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();
		
		// event
		sbResult.append(linePrefix);
		sbResult.append("import ");
		sbResult.append(PropertyUtil.getEntityBasePackage());
		sbResult.append(".event.");
		sbResult.append(PropertyUtil.getMethodNameCap());
		sbResult.append("Event;");

		sbResult.append(PropertyUtil.getLineSeperator());

		// command
		sbResult.append(linePrefix);
		sbResult.append("import ");
		sbResult.append(PropertyUtil.getEntityBasePackage());
		sbResult.append(".command.");
		sbResult.append(PropertyUtil.getMethodNameCap());
		sbResult.append("Command;");
		
		return sbResult.toString();
	}
}
