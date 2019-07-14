@Override public DatabaseInfo executeQuery(DatabaseConfiguration dbConfig,String query) throws DatabaseServiceException {
  try {
    Connection connection=MariaDBConnectionManager.getInstance().getConnection(dbConfig,false);
    Statement statement=connection.createStatement();
    ResultSet queryResult=statement.executeQuery(query);
    MariaDbResultSetMetaData metadata=(MariaDbResultSetMetaData)queryResult.getMetaData();
    int columnCount=metadata.getColumnCount();
    ArrayList<DatabaseColumn> columns=new ArrayList<DatabaseColumn>(columnCount);
    for (int i=1; i <= columnCount; i++) {
      DatabaseColumn dc=new DatabaseColumn(metadata.getColumnName(i),metadata.getColumnLabel(i),DatabaseUtils.getDbColumnType(metadata.getColumnType(i)),metadata.getColumnDisplaySize(i));
      columns.add(dc);
    }
    int index=0;
    List<DatabaseRow> rows=new ArrayList<DatabaseRow>();
    while (queryResult.next()) {
      DatabaseRow row=new DatabaseRow();
      row.setIndex(index);
      List<String> values=new ArrayList<String>(columnCount);
      for (int i=1; i <= columnCount; i++) {
        values.add(queryResult.getString(i));
      }
      row.setValues(values);
      rows.add(row);
      index++;
    }
    DatabaseInfo dbInfo=new DatabaseInfo();
    dbInfo.setColumns(columns);
    dbInfo.setRows(rows);
    return dbInfo;
  }
 catch (  SQLException e) {
    logger.error("SQLException::",e);
    throw new DatabaseServiceException(true,e.getSQLState(),e.getErrorCode(),e.getMessage());
  }
 finally {
    MariaDBConnectionManager.getInstance().shutdown();
  }
}
