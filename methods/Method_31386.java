@Override protected MySQLTable[] doAllTables() throws SQLException {
  List<String> tableNames=jdbcTemplate.queryForStringList("SELECT table_name FROM information_schema.tables WHERE table_schema=?" + " AND table_type='BASE TABLE'",name);
  MySQLTable[] tables=new MySQLTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new MySQLTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
