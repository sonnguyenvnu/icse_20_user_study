@Override protected RedshiftTable[] doAllTables() throws SQLException {
  List<String> tableNames=jdbcTemplate.queryForStringList("SELECT t.table_name FROM information_schema.tables t" + " WHERE table_schema=?" + " AND table_type='BASE TABLE'",name);
  RedshiftTable[] tables=new RedshiftTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new RedshiftTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
