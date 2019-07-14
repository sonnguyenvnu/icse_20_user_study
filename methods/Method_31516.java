@Override protected SQLServerTable[] doAllTables() throws SQLException {
  List<String> tableNames=new ArrayList<>();
  for (  DBObject table : queryDBObjects(ObjectType.USER_TABLE)) {
    tableNames.add(table.name);
  }
  SQLServerTable[] tables=new SQLServerTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new SQLServerTable(jdbcTemplate,database,databaseName,this,tableNames.get(i));
  }
  return tables;
}
