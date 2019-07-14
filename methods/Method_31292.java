/** 
 * Builds the drop statements for database objects in this schema.
 * @param dropPrefix The drop command for the database object (e.g. 'drop table').
 * @param query      The query to get all present database objects
 * @return The statements.
 * @throws SQLException when the drop statements could not be built.
 */
private List<String> buildDropStatements(final String dropPrefix,final String query) throws SQLException {
  List<String> dropStatements=new ArrayList<>();
  List<String> dbObjects=jdbcTemplate.queryForStringList(query);
  for (  String dbObject : dbObjects) {
    dropStatements.add(dropPrefix + " " + database.quote(name,dbObject));
  }
  return dropStatements;
}
