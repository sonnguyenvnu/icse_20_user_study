@Override protected DerbyTable[] doAllTables() throws SQLException {
  List<String> tableNames=listObjectNames("TABLE","TABLETYPE='T'");
  DerbyTable[] tables=new DerbyTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new DerbyTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
