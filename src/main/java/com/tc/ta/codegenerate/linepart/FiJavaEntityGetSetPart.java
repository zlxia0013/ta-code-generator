package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FiJavaEntityGetSetPart extends Part {

	public FiJavaEntityGetSetPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();
		
		sbResult.append(linePrefix);

		// getter
		sbResult.append("public ").append(PropertyUtil.fieldType).append(" get").append(Util.capStr(PropertyUtil.fieldName)).append("() {").append(
				PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t").append("return ").append(PropertyUtil.fieldName).append(";").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("}").append(PropertyUtil.getLineSeperator());

		// setter
		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("public void set").append(Util.capStr(PropertyUtil.fieldName)).append("(").append(PropertyUtil.fieldType)
				.append(" ").append(PropertyUtil.fieldName).append(") {").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t").append("this.").append(PropertyUtil.fieldName).append(" = ").append(PropertyUtil.fieldName).append(
				";").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("}");

		return sbResult.toString();
	}
}
