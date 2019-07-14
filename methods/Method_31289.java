/** 
 * Generates DROP statements for the sequences in this schema.
 * @return The drop statements.
 * @throws SQLException when the statements could not be generated.
 */
private List<String> generateDropStatementsForSequences() throws SQLException {
  String dropSeqGenQuery="select SEQNAME from SYSCAT.SEQUENCES where SEQSCHEMA = '" + name + "' and SEQTYPE='S'";
  return buildDropStatements("DROP SEQUENCE",dropSeqGenQuery);
}
