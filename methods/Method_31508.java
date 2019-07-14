/** 
 * Cleans the computed columns in this schema.
 * @param tables the tables to be cleaned
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanComputedColumns(List<DBObject> tables) throws SQLException {
  List<String> statements=new ArrayList<>();
  for (  DBObject table : tables) {
    String tableName=database.quote(name,table.name);
    List<String> columns=jdbcTemplate.queryForStringList("SELECT name FROM sys.computed_columns WHERE object_id=OBJECT_ID(N'" + tableName + "')");
    for (    String column : columns) {
      statements.add("ALTER TABLE " + tableName + " DROP COLUMN " + database.quote(column));
    }
  }
  return statements;
}
