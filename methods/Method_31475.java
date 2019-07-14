/** 
 * Generates DROP statements for this type of object in this schema.
 * @param objectType The type of object.
 * @return The drop statements.
 * @throws SQLException when the statements could not be generated.
 */
private List<String> generateDropStatements(String objectType) throws SQLException {
  List<String> dropStatements=new ArrayList<>();
  List<String> dbObjects=getDbObjects(objectType);
  for (  String dbObject : dbObjects) {
    dropStatements.add("DROP " + objectType + " " + database.quote(name,dbObject) + " CASCADE");
  }
  return dropStatements;
}
