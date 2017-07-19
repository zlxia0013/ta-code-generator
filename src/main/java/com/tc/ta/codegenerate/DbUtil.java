package com.tc.ta.codegenerate;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {
	private static Map<String, List<DbColumnProperty>>	metaDataCache	= new HashMap<String, List<DbColumnProperty>>();

	public static List<DbColumnProperty> getTableMetaData(String tableName) {
		List<DbColumnProperty> tableMetaData = metaDataCache.get(tableName);

		if (tableMetaData == null) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				String driverName = PropertyUtil.getProperty("dbDriverName");
				String url = PropertyUtil.getProperty("dbConnectionUrl");
				String userName = PropertyUtil.getProperty("dbUserName");
				String password = PropertyUtil.getProperty("dbPassword");

				Class.forName(driverName);
				conn = DriverManager.getConnection(url, userName, password);

				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from " + PropertyUtil.getTableName());
				tableMetaData = encapsulate(rs.getMetaData());

				metaDataCache.put(tableName, tableMetaData);
			} catch (Exception e) {
				throw new CgRuntimeException(e);
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return tableMetaData;
	}

	private static List<DbColumnProperty> encapsulate(ResultSetMetaData tableMetaData) {
		List<DbColumnProperty> lstResult = new ArrayList<DbColumnProperty>();

		try {
			for (int i = 1, len = tableMetaData.getColumnCount(); i <= len; i++) {
				if (tableMetaData.getColumnClassName(i).equals("oracle.jdbc.OracleBlob")) {
					continue;
				}

				DbColumnProperty column = new DbColumnProperty();
				column.setColumnName(tableMetaData.getColumnName(i));
				// column.setColumnType(tableMetaData.getColumnType(i));
				column.setColumnClassName(tableMetaData.getColumnClassName(i));
				column.setPrecision(tableMetaData.getPrecision(i));
				column.setScale(tableMetaData.getScale(i));
				lstResult.add(column);

				// //
				// StringBuilder sb = new StringBuilder();
				// sb.append(tableMetaData.getColumnName(i)).append(" -- ");
				// sb.append(tableMetaData.getColumnType(i)).append(" -- ");
				// sb.append(tableMetaData.getColumnTypeName(i)).append(" -- ");
				// sb.append(tableMetaData.getColumnDisplaySize(i)).append(" -- ");
				// sb.append(tableMetaData.getColumnClassName(i)).append(" -- ");
				// sb.append(tableMetaData.getColumnLabel(i)).append(" -- ");
				// sb.append(tableMetaData.getPrecision(i)).append(" -- ");
				// sb.append(tableMetaData.getScale(i)).append(" -- ");
				//
				// System.out.println(sb.toString());
			}
		} catch (SQLException e) {
			throw new CgRuntimeException(e);
		}

		return lstResult;
	}
}
