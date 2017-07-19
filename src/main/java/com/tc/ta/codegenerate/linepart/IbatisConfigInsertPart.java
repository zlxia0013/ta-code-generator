package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class IbatisConfigInsertPart extends Part {

	public IbatisConfigInsertPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("<sqlMap resource=\"");
		sbResult.append(Util.replaceAll(PropertyUtil.getEntityBasePackage(),
				".", "/"));
		sbResult.append("/pojo/");
		sbResult.append(PropertyUtil.getEntityClassName());
		sbResult.append("_SqlMap.xml\"/>");

		sbResult.append(PropertyUtil.getLineSeperator());

		return sbResult.toString();
	}
}
