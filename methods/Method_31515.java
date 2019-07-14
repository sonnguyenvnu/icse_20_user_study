/** 
 * Cleans the objects of these types in this schema.
 * @param dropQualifier The type of DROP statement to issue.
 * @param objectTypes   The type of objects to drop.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanObjects(String dropQualifier,ObjectType... objectTypes) throws SQLException {
  List<String> statements=new ArrayList<>();
  List<DBObject> dbObjects=queryDBObjects(objectTypes);
  for (  DBObject dbObject : dbObjects) {
    statements.add("DROP " + dropQualifier + " " + database.quote(name,dbObject.name));
  }
  return statements;
}
