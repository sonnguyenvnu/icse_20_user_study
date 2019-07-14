/** 
 * Cleans the unique constraints in this schema.
 * @param tables the tables to be cleaned
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanUniqueConstraints(List<DBObject> tables) throws SQLException {
  List<String> statements=new ArrayList<>();
  for (  DBObject table : tables) {
    List<DBObject> dfs=queryDBObjectsWithParent(table,ObjectType.DEFAULT_CONSTRAINT);
    for (    DBObject df : dfs) {
      statements.add("ALTER TABLE " + database.quote(name,table.name) + " DROP CONSTRAINT " + database.quote(df.name));
    }
  }
  return statements;
}
