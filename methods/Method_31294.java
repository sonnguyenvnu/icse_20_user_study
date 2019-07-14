private DB2Table[] findTables(String sqlQuery,String... params) throws SQLException {
  List<String> tableNames=jdbcTemplate.queryForStringList(sqlQuery,params);
  DB2Table[] tables=new DB2Table[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new DB2Table(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
