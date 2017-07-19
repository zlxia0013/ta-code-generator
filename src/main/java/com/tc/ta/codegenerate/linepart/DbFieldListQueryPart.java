package com.tc.ta.codegenerate.linepart;


import com.tc.ta.codegenerate.DbColumnProperty;
import com.tc.ta.codegenerate.DbUtil;
import com.tc.ta.codegenerate.PropertyUtil;

import java.io.File;
import java.util.List;

public class DbFieldListQueryPart extends Part {

	public DbFieldListQueryPart(String keyword, int lineNum, File file) {
		super(keyword, lineNum, file);
	}

	@Override
	public String getResult(String linePrefix) {
		StringBuilder sbResult = new StringBuilder();

		List<DbColumnProperty> lstColumns = DbUtil.getTableMetaData(PropertyUtil.getTableName());

		for (int i = 0, len = lstColumns.size(); i < len; i++) {
			DbColumnProperty column = lstColumns.get(i);

			if (i != 0) {
				sbResult.append(linePrefix);
			}

			sbResult.append(" t.");
			sbResult.append(column.getColumnName());
			sbResult.append(" as ");
			sbResult.append(column.getPojoVarName());

			if (i < len - 1) {
				sbResult.append(",").append(PropertyUtil.getLineSeperator());
			}
		}

		return sbResult.toString();
	}
}
