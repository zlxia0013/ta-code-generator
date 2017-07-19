package com.tc.ta.codegenerate;

import com.sun.xml.internal.ws.util.StringUtils;

public class DbColumnProperty {
	private String	columnName;

	private String	columnClassName;

	private int		precision;

	private int		scale;

	public DbColumnProperty() {

	}

	public String getColumnTypeFlex() {
		if ("java.math.BigDecimal".equalsIgnoreCase(columnClassName)) {
			if (scale == 0 || precision == 0) {
				return "Number";
			} else {
				return "String";
			}
		} else if ("java.lang.String".equalsIgnoreCase(columnClassName)) {
			return "String";
		} else if ("java.sql.Timestamp".equalsIgnoreCase(columnClassName)) {
			return "Date";
		}else if ("java.lang.Integer".equalsIgnoreCase(columnClassName)) {
			return "Integer";
		}
		// if (columnType == 4) {
		// return "int";
		// } else if (columnType == 12) {
		// return "String";
		// } else if (columnType == 3) {
		// return "Number";
		// } else if (columnType == 93) {
		// return "Date";
		// }

		throw new CgRuntimeException("Unsupported columnClassName: " + columnClassName + " of column:" + columnName);
	}

	public String getColumnTypeJava() {
		if ("java.math.BigDecimal".equalsIgnoreCase(columnClassName)) {
			if (scale == 0 || precision == 0) {
				return "Long";
			} else {
				return "String";
			}
		} else if ("java.lang.String".equalsIgnoreCase(columnClassName)) {
			return "String";
		} else if ("java.sql.Timestamp".equalsIgnoreCase(columnClassName)) {
			return "Date";
		}else if ("java.lang.Integer".equalsIgnoreCase(columnClassName)) {
			return "Integer";
		}

		// if (columnType == 4) {
		// return "Integer";
		// } else if (columnType == 12) {
		// return "String";
		// } else if (columnType == 3) {
		// return "BigDecimal";
		// } else if (columnType == 93) {
		// return "Date";
		// }

		throw new CgRuntimeException("Unsupported columnClassName: " + columnClassName + " of column:" + columnName);
	}

	public String getColumnTypeDB() {
		if ("java.math.BigDecimal".equalsIgnoreCase(columnClassName)) {
			return "Decimal";
//			if (scale == 0 || precision == 0) {
//				return "Integer";
//			} else {
//			}
		} else if ("java.lang.String".equalsIgnoreCase(columnClassName)) {
			return "Varchar";
		} else if ("java.sql.Timestamp".equalsIgnoreCase(columnClassName)) {
			return "Datetime";
		}else if ("java.lang.Integer".equalsIgnoreCase(columnClassName)) {
			return "int";
		}

		// if (columnType == 4) {
		// return "Integer";
		// } else if (columnType == 12) {
		// return "Varchar";
		// } else if (columnType == 3) {
		// return "Decimal";
		// } else if (columnType == 93) {
		// return "Datetime";
		// }

		throw new CgRuntimeException("Unsupported columnClassName: " + columnClassName + " of column:" + columnName);
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getPojoVarName() {
		String colNameSeperator = PropertyUtil.getColNameSeperator();

		if (colNameSeperator == null || colNameSeperator.trim().length() <= 0) {
			return this.columnName;
		} else {
			StringBuilder sb = new StringBuilder();
			String[] parts = this.columnName.split("_");
			for (String part : parts) {
				if (sb.length() <= 0) {
					sb.append(part.toLowerCase());
				} else {
					sb.append(StringUtils.capitalize(part.toLowerCase()));
				}
			}

			return sb.toString();
		}
	}

	public String getColumnClassName() {
		return columnClassName;
	}

	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

}
