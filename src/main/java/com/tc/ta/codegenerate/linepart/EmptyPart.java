package com.tc.ta.codegenerate.linepart;

public class EmptyPart extends Part {

	public EmptyPart() {
		super(null, -1, null);
	}

	@Override
	public String getResult(String linePrefix) {
		return "";
	}

}
