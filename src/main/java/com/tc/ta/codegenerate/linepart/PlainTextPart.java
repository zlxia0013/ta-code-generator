package com.tc.ta.codegenerate.linepart;

import java.io.File;

public class PlainTextPart extends Part {

	public PlainTextPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		return keyword;
	}

}
