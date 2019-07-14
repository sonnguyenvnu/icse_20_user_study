@Override protected void doClean() throws SQLException {
  boolean foreignKeys=jdbcTemplate.queryForBoolean("PRAGMA foreign_keys");
  if (foreignKeys) {
    jdbcTemplate.execute("PRAGMA foreign_keys = OFF");
  }
  List<String> viewNames=jdbcTemplate.queryForStringList("SELECT tbl_name FROM " + database.quote(name) + ".sqlite_master WHERE type='view'");
  for (  String viewName : viewNames) {
    jdbcTemplate.execute("DROP VIEW " + database.quote(name,viewName));
  }
  for (  Table table : allTables()) {
    table.drop();
  }
  if (getTable(SQLiteTable.SQLITE_SEQUENCE).exists()) {
    jdbcTemplate.execute("DELETE FROM " + SQLiteTable.SQLITE_SEQUENCE);
  }
  if (foreignKeys) {
    jdbcTemplate.execute("PRAGMA foreign_keys = ON");
  }
}
