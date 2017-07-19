package com.tc.ta.codegenerate.linepart;



import com.tc.ta.codegenerate.PropertyUtil;
import com.tc.ta.codegenerate.Util;

import java.io.File;

public class SpringConfigInsertPart extends Part {

	public SpringConfigInsertPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("<import resource=\"classpath*:/");
		sbResult.append(Util.replaceAll(PropertyUtil.getEntityBasePackage(),
				".", "/"));
		sbResult.append("/pojo/");
		sbResult.append(PropertyUtil.getEntityClassName());
		sbResult.append("_Beans.xml\"/>");
		
		sbResult.append(PropertyUtil.getLineSeperator());

		return sbResult.toString();
	}
}
