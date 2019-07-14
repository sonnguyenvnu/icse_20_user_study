/** 
 * Generates the statements to drop the sequences in this schema.
 * @return The drop statements.
 * @throws SQLException when the drop statements could not be generated.
 */
private List<String> generateDropStatementsForSequences() throws SQLException {
  List<String> sequenceNames=jdbcTemplate.queryForStringList("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SYSTEM_SEQUENCES where SEQUENCE_SCHEMA = ?",name);
  List<String> statements=new ArrayList<>();
  for (  String seqName : sequenceNames) {
    statements.add("DROP SEQUENCE " + database.quote(name,seqName));
  }
  return statements;
}
