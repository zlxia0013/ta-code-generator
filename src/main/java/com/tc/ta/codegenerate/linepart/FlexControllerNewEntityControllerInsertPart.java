package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FlexControllerNewEntityControllerInsertPart extends Part {

	public FlexControllerNewEntityControllerInsertPart(String keyword, int lineNum,
			File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("new ");
		sbResult.append(PropertyUtil.getEntityClassName());
		sbResult.append("Controller(this);");

		return sbResult.toString();
	}
}
