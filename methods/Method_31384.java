/** 
 * Generate the statements to clean the views in this schema.
 * @return The list of statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanViews() throws SQLException {
  List<String> viewNames=jdbcTemplate.queryForStringList("SELECT table_name FROM information_schema.views WHERE table_schema=?",name);
  List<String> statements=new ArrayList<>();
  for (  String viewName : viewNames) {
    statements.add("DROP VIEW " + database.quote(name,viewName));
  }
  return statements;
}
