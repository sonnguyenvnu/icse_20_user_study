/** 
 * Cleans the default constraints in this schema.
 * @param tables the tables to be cleaned
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanDefaultConstraints(List<DBObject> tables) throws SQLException {
  List<String> statements=new ArrayList<>();
  for (  DBObject table : tables) {
    String tableName=database.quote(name,table.name);
    List<String> indexes=jdbcTemplate.queryForStringList("SELECT name FROM sys.indexes" + " WHERE object_id=OBJECT_ID(N'" + tableName + "')" + " AND is_primary_key = 0" + " AND is_unique_constraint = 1" + " AND name IS NOT NULL");
    for (    String index : indexes) {
      statements.add("ALTER TABLE " + tableName + " DROP CONSTRAINT " + database.quote(index));
    }
  }
  return statements;
}
