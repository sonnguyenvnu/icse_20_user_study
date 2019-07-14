@Override protected SQLiteTable[] doAllTables() throws SQLException {
  List<String> tableNames=jdbcTemplate.queryForStringList("SELECT tbl_name FROM " + database.quote(name) + ".sqlite_master WHERE type='table'");
  SQLiteTable[] tables=new SQLiteTable[tableNames.size()];
  for (int i=0; i < tableNames.size(); i++) {
    tables[i]=new SQLiteTable(jdbcTemplate,database,this,tableNames.get(i));
  }
  return tables;
}
