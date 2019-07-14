/** 
 * Generates DROP statements for the triggers in this schema.
 * @return The drop statements.
 * @throws SQLException when the statements could not be generated.
 */
private List<String> generateDropStatementsForTriggers() throws SQLException {
  String dropTrigGenQuery="select TRIGNAME from SYSCAT.TRIGGERS where TRIGSCHEMA = '" + name + "'";
  return buildDropStatements("DROP TRIGGER",dropTrigGenQuery);
}
