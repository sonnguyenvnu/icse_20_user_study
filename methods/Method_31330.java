@Override protected H2Table[] doAllTables() throws SQLException {
  List<String> tableNames=listObjectNames("TABLE","TABLE_TYPE = 'TABLE'");
  H2Table[] tables=new H2Table[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new H2Table(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
