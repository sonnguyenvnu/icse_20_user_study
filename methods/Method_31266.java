/** 
 * Generates the statements for dropping the views in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForViews() throws SQLException {
  List<String> names=jdbcTemplate.queryForStringList("SELECT table_name FROM information_schema.views" + " WHERE table_catalog=? AND table_schema='public'",name);
  List<String> statements=new ArrayList<>();
  for (  String name : names) {
    statements.add("DROP VIEW IF EXISTS " + database.quote(this.name,name) + " CASCADE");
  }
  return statements;
}
