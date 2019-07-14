/** 
 * Cleans the types in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanTypes() throws SQLException {
  List<String> typeNames=jdbcTemplate.queryForStringList("SELECT t.name FROM sys.types t INNER JOIN sys.schemas s ON t.schema_id = s.schema_id" + " WHERE t.is_user_defined = 1 AND s.name = ?",name);
  List<String> statements=new ArrayList<>();
  for (  String typeName : typeNames) {
    statements.add("DROP TYPE " + database.quote(name,typeName));
  }
  return statements;
}
