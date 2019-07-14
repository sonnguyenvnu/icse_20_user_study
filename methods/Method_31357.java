private InformixTable[] findTables(String sqlQuery,String... params) throws SQLException {
  List<String> tableNames=jdbcTemplate.queryForStringList(sqlQuery,params);
  InformixTable[] tables=new InformixTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new InformixTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
