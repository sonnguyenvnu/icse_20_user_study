/** 
 * Writes the result set of a query to a file in the CSV format.
 * @param conn the connection
 * @param outputFileName the file name
 * @param sql the query
 * @param charset the charset or null to use the system default charset(see system property file.encoding)
 * @return the number of rows written
 */
public int write(Connection conn,String outputFileName,String sql,String charset) throws SQLException {
  Statement stat=conn.createStatement();
  ResultSet rs=stat.executeQuery(sql);
  int rows=write(outputFileName,rs,charset);
  stat.close();
  return rows;
}
