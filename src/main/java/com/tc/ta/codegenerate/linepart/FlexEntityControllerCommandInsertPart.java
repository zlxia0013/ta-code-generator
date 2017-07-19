package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FlexEntityControllerCommandInsertPart extends Part {

	public FlexEntityControllerCommandInsertPart(String keyword, int lineNum,
			File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("parentController.addCommand(");
		sbResult.append(PropertyUtil.getMethodNameCap());
		sbResult.append("Event.EVENT_NAME, ");
		sbResult.append(PropertyUtil.getMethodNameCap());
		sbResult.append("Command);");

		return sbResult.toString();
	}
}
