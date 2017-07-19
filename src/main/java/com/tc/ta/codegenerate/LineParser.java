package com.tc.ta.codegenerate;


import com.tc.ta.codegenerate.linepart.Part;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LineParser {

	public LineParser() {
	}

	public String parse(String line, int lineNum, File file) {
		StringBuilder sbResult = new StringBuilder();

		List<Part> lstParts = parseLineElement(line, lineNum, file);

		Part holderPart = getInsertHolderSymbolPart(lstParts);

		if (holderPart == null) {
			for (Part linePart : lstParts) {
				sbResult.append(linePart.getResult(getFirstPartBlank(lstParts)));
			}
		} else {
			sbResult.append(holderPart.getResult(getFirstPartBlank(lstParts)));

			sbResult.append(PropertyUtil.getLineSeperator());

			sbResult.append(line).append(PropertyUtil.getLineSeperator());
		}

		return sbResult.toString();
	}

	private String getFirstPartBlank(List<Part> lstParts) {
		Part firstPart = lstParts.get(0);

		String tmp = firstPart.getResult("");
		if (tmp != null) {
			if (tmp.trim().length() == 0) {
				return tmp;
			}

			return tmp.substring(0, tmp.indexOf(tmp.trim()));
		}

		return "";
	}

	private Part getInsertHolderSymbolPart(List<Part> lstParts) {
		for (Part linePart : lstParts) {
			if (linePart.isInsertHolderSymbol()) {
				return linePart;
			}
		}

		return null;
	}

	private List<Part> parseLineElement(String line, int lineNum, File file) {
		List<Part> lstParts = new ArrayList<Part>();
		int start, end;
		String tmp1 = line;
		int baseStart = 0;

		while ((start = tmp1.indexOf(PropertyUtil.KEYWORD_SEPERATOR, baseStart)) >= 0
				&& (end = tmp1.indexOf(PropertyUtil.KEYWORD_SEPERATOR, start + PropertyUtil.KEYWORD_SEPERATOR.length())) >= 0) {
			String keyword = tmp1.substring(start, end + PropertyUtil.KEYWORD_SEPERATOR.length());

			if (!needProcess(keyword)) {
				baseStart = end + PropertyUtil.KEYWORD_SEPERATOR.length();
				continue;
			}

			lstParts.add(Part.getPart(tmp1.substring(0, start), lineNum, file));

			lstParts.add(Part.getPart(keyword, lineNum, file));

			tmp1 = tmp1.substring(end + PropertyUtil.KEYWORD_SEPERATOR.length());
			baseStart = 0;
		}

		lstParts.add(Part.getPart(tmp1, lineNum, file));

		lstParts.add(Part.getPart(PropertyUtil.getLineSeperator(), lineNum, file)); // new
		// line

		return lstParts;
	}

	private boolean needProcess(String keyword) {
		String keywordEs = keyword.substring(PropertyUtil.KEYWORD_SEPERATOR.length(), keyword.lastIndexOf(PropertyUtil.KEYWORD_SEPERATOR));

		if (keywordEs.indexOf(PropertyUtil.KEYWORD_CONTROL_SP) <= 0) {
			return true;
		}

		String kwControlStr = keywordEs.substring(0, keywordEs.indexOf(PropertyUtil.KEYWORD_CONTROL_SP));

		String[] kwControls = kwControlStr.split(PropertyUtil.KEYWORD_CONTROL_INNER_SP);

		if (PropertyUtil.GEN_TYPE_TABLE.equalsIgnoreCase(PropertyUtil.generateType)) {
			if (contain(kwControls, PropertyUtil.KEYWORD_NO_PROCESS_TABLE) || contain(kwControls, PropertyUtil.KEYWORD_PROCESS_ONLY_FOR_INS_FLD)) {
				return false;
			}
		} else if (PropertyUtil.GEN_TYPE_METHOD.equalsIgnoreCase(PropertyUtil.generateType)) {
			if (contain(kwControls, PropertyUtil.KEYWORD_NO_PROCESS_METHOD) || contain(kwControls, PropertyUtil.KEYWORD_PROCESS_ONLY_FOR_INS_FLD)) {
				return false;
			}
		} else if (PropertyUtil.GEN_TYPE_FIELD_INSERTER.equalsIgnoreCase(PropertyUtil.generateType)) {
			if (contain(kwControls, PropertyUtil.KEYWORD_PROCESS_ONLY_FOR_INS_FLD)) {
				return true;
			} else {
				return false;
			}
		}

		return true;
	}

	private boolean contain(String[] kwControls, String key) {
		for (String tmpKey : kwControls) {
			if (tmpKey != null && key != null) {
				if (tmpKey.trim().equals(key.trim())) {
					return true;
				}
			}
		}

		return false;
	}

}
