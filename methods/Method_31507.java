/** 
 * Cleans the foreign keys in this schema.
 * @param tables the tables to be cleaned
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanForeignKeys(List<DBObject> tables) throws SQLException {
  List<String> statements=new ArrayList<>();
  for (  DBObject table : tables) {
    List<DBObject> fks=queryDBObjectsWithParent(table,ObjectType.FOREIGN_KEY,ObjectType.CHECK_CONSTRAINT);
    for (    DBObject fk : fks) {
      statements.add("ALTER TABLE " + database.quote(name,table.name) + " DROP CONSTRAINT " + database.quote(fk.name));
    }
  }
  return statements;
}
