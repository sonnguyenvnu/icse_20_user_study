@Override protected OracleTable[] doAllTables() throws SQLException {
  List<String> tableNames=TABLE.getObjectNames(jdbcTemplate,database,this);
  OracleTable[] tables=new OracleTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new OracleTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
