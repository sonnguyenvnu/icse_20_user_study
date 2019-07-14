@Override protected CockroachDBTable[] doAllTables() throws SQLException {
  String query;
  if (cockroachDB1) {
    query="SELECT table_name FROM information_schema.tables" + " WHERE table_schema=?" + " AND table_type='BASE TABLE'";
  }
 else {
    query="SELECT table_name FROM information_schema.tables" + " WHERE table_catalog=?" + " AND table_schema='public'" + " AND table_type='BASE TABLE'";
  }
  List<String> tableNames=jdbcTemplate.queryForStringList(query,name);
  CockroachDBTable[] tables=new CockroachDBTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new CockroachDBTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
