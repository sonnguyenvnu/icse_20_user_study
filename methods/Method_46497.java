public TableStruct analyse(Connection connection,String table) throws SQLException {
  ResultSet structRs=null;
  ResultSet columnSet=null;
  TableStruct tableStruct=new TableStruct(table);
  try {
    structRs=connection.getMetaData().getPrimaryKeys(connection.getCatalog(),null,table);
    columnSet=connection.getMetaData().getColumns(null,"%",table,"%");
    while (structRs.next()) {
      tableStruct.getPrimaryKeys().add(structRs.getString("COLUMN_NAME"));
    }
    while (columnSet.next()) {
      tableStruct.getColumns().put(columnSet.getString("COLUMN_NAME"),columnSet.getString("TYPE_NAME"));
    }
  }
 catch (  SQLException e) {
    try {
      DbUtils.close(structRs);
      DbUtils.close(columnSet);
    }
 catch (    SQLException ignored) {
    }
    throw e;
  }
  return tableStruct;
}
