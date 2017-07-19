package com.tc.ta;


import com.tc.ta.codegenerate.CodeGenerator;

public class TableGenerator {
	public static void main(String[] args) {
		String tableName = "sys_privilege";

		CodeGenerator cg = new CodeGenerator();
		cg.startGenerateTable(tableName);

		System.out.println("successful.");
	}
}
