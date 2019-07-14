public TableStruct analyse(String table) throws SQLException {
  Connection connection=null;
  try {
    DTXLocalContext.makeUnProxy();
    connection=dataSource.getConnection();
    connection.setAutoCommit(true);
    return analyse(connection,table);
  }
  finally {
    DTXLocalContext.undoProxyStatus();
    DbUtils.close(connection);
  }
}
