@Override protected HSQLDBTable[] doAllTables() throws SQLException {
  List<String> tableNames=jdbcTemplate.queryForStringList("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.SYSTEM_TABLES where TABLE_SCHEM = ? AND TABLE_TYPE = 'TABLE'",name);
  HSQLDBTable[] tables=new HSQLDBTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new HSQLDBTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
