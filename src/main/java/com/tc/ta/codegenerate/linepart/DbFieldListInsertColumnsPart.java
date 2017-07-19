package com.tc.ta.codegenerate.linepart;

import com.tc.ta.codegenerate.DbColumnProperty;
import com.tc.ta.codegenerate.DbUtil;
import com.tc.ta.codegenerate.PropertyUtil;

import java.io.File;
import java.util.List;

public class DbFieldListInsertColumnsPart extends Part {

	public DbFieldListInsertColumnsPart(String keyword, int lineNum, File file) {
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

			sbResult.append("<if test=\"");
			sbResult.append(column.getPojoVarName());
			sbResult.append("!= null\">");
			sbResult.append(column.getColumnName());
			sbResult.append(",</if>");

			// sbResult.append(column.getColumnName());

			if (i < len - 1) {
				// sbResult.append(",");
				sbResult.append(PropertyUtil.getLineSeperator());
			}
		}

		return sbResult.toString();
	}
}
