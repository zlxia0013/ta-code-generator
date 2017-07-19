package com.tc.ta.codegenerate.linepart;


import com.tc.ta.codegenerate.*;

import java.io.File;
import java.util.List;

public class DbFieldListUpdatePart extends Part {

	public DbFieldListUpdatePart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		List<DbColumnProperty> lstColumns = DbUtil.getTableMetaData(PropertyUtil.getTableName());

		boolean isFirstElement = true;

		for (int i = 0, len = lstColumns.size(); i < len; i++) {
			DbColumnProperty column = lstColumns.get(i);

			if (column.getColumnName().equalsIgnoreCase("id")) {
				continue;
			}

			if (isFirstElement) {
				isFirstElement = false;
			} else {
				sbResult.append(linePrefix);
			}

			if (column.getColumnName().equalsIgnoreCase("timeAdd")) {
				sbResult.append(column.getColumnName());
				sbResult.append(" = now()");
			} else {
				sbResult.append("<if test=\"") .append(column.getPojoVarName()) .append("!= null\" >");
				sbResult.append(column.getColumnName());
				sbResult.append(" = #{");
				sbResult.append(column.getPojoVarName());
				sbResult.append("}");
				sbResult.append(",</if>");
			}

			if (i < len - 1) {
				sbResult.append(PropertyUtil.getLineSeperator());
			}
		}

		return sbResult.toString();
	}
}
