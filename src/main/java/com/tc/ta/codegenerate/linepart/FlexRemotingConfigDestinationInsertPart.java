package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FlexRemotingConfigDestinationInsertPart extends Part {

	public FlexRemotingConfigDestinationInsertPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix).append("<destination id=\"").append(PropertyUtil.getEntityClassNameVar()).append(
				"Bo\">").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t<properties>").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t\t<factory>spring</factory>").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t\t<source>").append((PropertyUtil.getEntityClassNameVar())).append(
				"Bo</source>").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t</properties>").append(PropertyUtil.getLineSeperator());

		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t<security>").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t\t<security-constraint ref=\"authenticated\" />").append(
				PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t</security>").append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("</destination>").append(PropertyUtil.getLineSeperator());

		return sbResult.toString();
	}
}
