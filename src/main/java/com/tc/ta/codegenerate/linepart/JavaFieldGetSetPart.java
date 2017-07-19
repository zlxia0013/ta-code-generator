package com.tc.ta.codegenerate.linepart;


import com.tc.ta.codegenerate.*;

import java.io.File;
import java.util.List;

public class JavaFieldGetSetPart extends Part {

	public JavaFieldGetSetPart(String keyword, int lineNum, File file) {
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

			// getter
			sbResult.append("public ").append(column.getColumnTypeJava())
					.append(" get").append(Util.capStr(column.getPojoVarName()))
					.append("() {").append(PropertyUtil.getLineSeperator());

			sbResult.append(linePrefix).append("\t").append("return ").append(
					column.getPojoVarName()).append(";").append(
					PropertyUtil.getLineSeperator());

			sbResult.append(linePrefix).append("}").append(
					PropertyUtil.getLineSeperator());

			// setter
			sbResult.append(PropertyUtil.getLineSeperator());

			sbResult.append(linePrefix).append("public void set").append(
					Util.capStr(column.getPojoVarName())).append("(").append(
					column.getColumnTypeJava()).append(" ").append(
					column.getPojoVarName()).append(") {").append(
					PropertyUtil.getLineSeperator());

			sbResult.append(linePrefix).append("\t").append("this.").append(
					column.getPojoVarName()).append(" = ").append(
					column.getPojoVarName()).append(";").append(
					PropertyUtil.getLineSeperator());

			sbResult.append(linePrefix).append("}");

			if (i < len - 1) {
				sbResult.append(PropertyUtil.getLineSeperator());
				sbResult.append(PropertyUtil.getLineSeperator());
			}
		}

		return sbResult.toString();
	}
}
