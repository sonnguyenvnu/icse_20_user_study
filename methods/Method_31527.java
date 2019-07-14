@Override protected SybaseASETable[] doAllTables() throws SQLException {
  List<String> tableNames=retrieveAllTableNames();
  SybaseASETable[] result=new SybaseASETable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    String tableName=tableNames.get(i);
    result[i]=new SybaseASETable(jdbcTemplate,database,this,tableName);
  }
  return result;
}
