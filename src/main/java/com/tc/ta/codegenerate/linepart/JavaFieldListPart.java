package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.DbColumnProperty;
import com.tc.ta.codegenerate.DbUtil;
import com.tc.ta.codegenerate.PropertyUtil;

import java.io.File;
import java.util.List;

public class JavaFieldListPart extends Part {

	public JavaFieldListPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		List<DbColumnProperty> lstColumns = DbUtil
				.getTableMetaData(PropertyUtil.getTableName());

		for (int i = 0, len = lstColumns.size(); i < len; i++) {
			DbColumnProperty column = lstColumns.get(i);

			if (i != 0) {
				sbResult.append(linePrefix);
			}

			sbResult.append("private ");
			sbResult.append(column.getColumnTypeJava());
			sbResult.append(" ");
			sbResult.append(column.getPojoVarName());
			sbResult.append(";");

			if (i < len - 1) {
				sbResult.append(PropertyUtil.getLineSeperator()).append(
						PropertyUtil.getLineSeperator());
			}
		}

		return sbResult.toString();
	}

}
