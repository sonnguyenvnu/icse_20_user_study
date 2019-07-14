@Override protected SAPHANATable[] doAllTables() throws SQLException {
  List<String> tableNames=getDbObjects("TABLE");
  SAPHANATable[] tables=new SAPHANATable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new SAPHANATable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
