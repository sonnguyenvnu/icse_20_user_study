package com.zheng.common.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC工具类
 * Created by ZhangShuzheng on 2017/1/10.
 */
public class JdbcUtil {

	// 定义数�?�库的链接
	private Connection conn;
	// 定义sql语�?�的执行对象
	private PreparedStatement pstmt;
	// 定义查询返回的结果集�?�
	private ResultSet rs;

	// �?始化
	public JdbcUtil(String driver, String url, String username, String password) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("数�?�库连接�?功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 更新数�?�
	public boolean updateByParams(String sql, List params) throws SQLException {
		// 影�?行数
		int result = -1;
		pstmt = conn.prepareStatement(sql);
		int index = 1;
		// 填充sql语�?�中的�?��?符
		if (null != params && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i ++) {
				pstmt.setObject(index ++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		return result > 0 ? true : false;
	}

	// 查询多�?�记录
	public List<Map> selectByParams(String sql, List params) throws SQLException {
		List<Map> list = new ArrayList<> ();
		int index = 1;
		pstmt = conn.prepareStatement(sql);
		if (null != params && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i ++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int colsLen = metaData.getColumnCount();
		while (rs.next()) {
			Map map = new HashMap(colsLen);
			for (int i = 0; i < colsLen; i ++) {
				String columnName = metaData.getColumnName(i + 1);
				Object columnValue = rs.getObject(columnName);
				if (null == columnValue) {
					columnValue = "";
				}
				map.put(columnName, columnValue);
			}
			list.add(map);
		}
		return list;
	}

	// 释放连接
	public void release() {
		try {
			if (null != rs) {
                rs.close();
            }
			if (null != pstmt) {
                pstmt.close();
            }
			if (null != conn) {
                conn.close();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("释放数�?�库连接");
	}

}
