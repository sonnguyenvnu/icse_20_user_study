/** 
 * Generates the statements for dropping the sequences in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForSequences() throws SQLException {
  List<String> sequenceNames=jdbcTemplate.queryForStringList("SELECT sequence_name FROM information_schema.sequences WHERE sequence_schema=?",name);
  List<String> statements=new ArrayList<>();
  for (  String sequenceName : sequenceNames) {
    statements.add("DROP SEQUENCE IF EXISTS " + database.quote(name,sequenceName));
  }
  return statements;
}
