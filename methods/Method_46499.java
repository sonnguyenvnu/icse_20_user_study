/** 
 * ???????
 * @param tableName tableName
 * @return exists
 * @throws SQLException SQLException
 */
public boolean existsTable(String tableName) throws SQLException {
  Connection connection=null;
  try {
    DTXLocalContext.makeUnProxy();
    connection=dataSource.getConnection();
    connection.setAutoCommit(true);
    return existsTable(connection,tableName);
  }
  finally {
    DbUtils.close(connection);
    DTXLocalContext.undoProxyStatus();
  }
}
