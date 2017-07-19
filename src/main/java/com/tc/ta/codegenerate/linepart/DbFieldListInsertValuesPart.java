package com.tc.ta.codegenerate.linepart;



import com.tc.ta.codegenerate.DbColumnProperty;
import com.tc.ta.codegenerate.DbUtil;
import com.tc.ta.codegenerate.PropertyUtil;

import java.io.File;
import java.util.List;

public class DbFieldListInsertValuesPart extends Part {

	public DbFieldListInsertValuesPart(String keyword, int lineNum, File file) {
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
				sbResult.append("now()");
			} else {
				// sbResult.append("#");
				// sbResult.append(column.getPojoVarName());
				// sbResult.append(":");
				// sbResult.append(column.getColumnTypeDB());
				// sbResult.append("#");

				sbResult.append("<if test=\"");
				sbResult.append(column.getPojoVarName());
				sbResult.append(" != null\" >");

				sbResult.append("#{");
				sbResult.append(column.getPojoVarName());
				sbResult.append("}");

				sbResult.append(",</if>");
			}

			if (i < len - 1) {
				// sbResult.append(",");
				sbResult.append(PropertyUtil.getLineSeperator());
			}
		}

		return sbResult.toString();
	}
}
