/** 
 * Generates the statements for dropping the sequences in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForSequences() throws SQLException {
  List<String> names=jdbcTemplate.queryForStringList("SELECT sequence_name FROM information_schema.sequences" + " WHERE sequence_catalog=? AND sequence_schema='public'",name);
  List<String> statements=new ArrayList<>();
  for (  String name : names) {
    statements.add("DROP SEQUENCE IF EXISTS " + database.quote(this.name,name) + " CASCADE");
  }
  return statements;
}
