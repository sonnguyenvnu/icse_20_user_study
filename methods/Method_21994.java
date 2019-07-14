private void createTaskIdIndexIfNeeded(final Connection conn,final String tableName,final String indexName) throws SQLException {
  DatabaseMetaData dbMetaData=conn.getMetaData();
  try (ResultSet resultSet=dbMetaData.getIndexInfo(null,null,tableName,false,false)){
    boolean hasTaskIdIndex=false;
    while (resultSet.next()) {
      if (indexName.equals(resultSet.getString("INDEX_NAME"))) {
        hasTaskIdIndex=true;
      }
    }
    if (!hasTaskIdIndex) {
      createTaskIdAndStateIndex(conn,tableName);
    }
  }
 }
