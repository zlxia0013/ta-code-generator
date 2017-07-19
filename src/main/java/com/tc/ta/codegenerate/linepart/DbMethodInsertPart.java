package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class DbMethodInsertPart extends Part {

	public DbMethodInsertPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("<select id=\"");
		sbResult.append(PropertyUtil.methodName);
		sbResult.append("\" parameterClass=\"");
		sbResult.append(PropertyUtil.methodParamTypeName);
		sbResult.append("\" resultClass=\"");
		sbResult.append(PropertyUtil.methodReturnTypeName);
		sbResult.append("\">");
		sbResult.append(PropertyUtil.getLineSeperator());
		
		sbResult.append(linePrefix);
		sbResult.append("\tSELECT *");
		sbResult.append(PropertyUtil.getLineSeperator());
		
		sbResult.append(linePrefix);
		sbResult.append("\tFROM ");
		sbResult.append(PropertyUtil.getTableName());
		sbResult.append(" a");
		sbResult.append(PropertyUtil.getLineSeperator());
		
		sbResult.append(linePrefix);
		sbResult.append("\t<dynamic prepend=\"WHERE\">");
		sbResult.append(PropertyUtil.getLineSeperator());
		
		sbResult.append(linePrefix);
		sbResult.append("\t\t1=2");
		sbResult.append(PropertyUtil.getLineSeperator());
		
		sbResult.append(linePrefix);
		sbResult.append("\t</dynamic>");
		sbResult.append(PropertyUtil.getLineSeperator());
		
		sbResult.append(linePrefix);
		sbResult.append("</select>");
		sbResult.append(PropertyUtil.getLineSeperator());

		return sbResult.toString();
	}
}
