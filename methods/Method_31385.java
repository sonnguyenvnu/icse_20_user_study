/** 
 * Generate the statements to clean the sequences in this schema.
 * @return The list of statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanSequences() throws SQLException {
  List<String> names=jdbcTemplate.queryForStringList("SELECT table_name FROM information_schema.tables WHERE table_schema=?" + " AND table_type='SEQUENCE'",name);
  List<String> statements=new ArrayList<>();
  for (  String name : names) {
    statements.add("DROP SEQUENCE " + database.quote(this.name,name));
  }
  return statements;
}
