@Override protected PostgreSQLTable[] doAllTables() throws SQLException {
  List<String> tableNames=jdbcTemplate.queryForStringList("SELECT t.table_name FROM information_schema.tables t" + " LEFT JOIN pg_depend dep ON dep.objid = (quote_ident(t.table_schema)||'.'||quote_ident(t.table_name))::regclass::oid AND dep.deptype = 'e'" + " WHERE table_schema=?" + " AND table_type='BASE TABLE'" + " AND dep.objid IS NULL" + " AND NOT (SELECT EXISTS (SELECT inhrelid FROM pg_catalog.pg_inherits" + " WHERE inhrelid = (quote_ident(t.table_schema)||'.'||quote_ident(t.table_name))::regclass::oid))",name);
  PostgreSQLTable[] tables=new PostgreSQLTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new PostgreSQLTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
