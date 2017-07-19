package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.*;

import java.io.File;

public class FlexEntityDelegateMethodInsertPart extends Part {

	public FlexEntityDelegateMethodInsertPart(String keyword, int lineNum,
			File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		sbResult.append(linePrefix);
		sbResult.append("public function ");
		sbResult.append(PropertyUtil.getMethodNameDeCap());
		sbResult.append("(");
		sbResult.append(PropertyUtil.methodParamVarName);
		sbResult.append(":");
		sbResult.append(PropertyUtil.methodParamTypeName);
		sbResult.append("):void");
		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("{");
		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t");
		sbResult.append("var call : Object = service.");
		sbResult.append(PropertyUtil.getMethodNameDeCap());
		sbResult.append("(");
		sbResult.append(PropertyUtil.methodParamVarName);
		sbResult.append(");");
		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("\t");
		sbResult.append("call.addResponder(responder);");
		sbResult.append(PropertyUtil.getLineSeperator());

		sbResult.append(linePrefix).append("}");
		sbResult.append(PropertyUtil.getLineSeperator());

		return sbResult.toString();
	}
}
