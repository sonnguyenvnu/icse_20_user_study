/** 
 * Generates DROP statements for the procedures in this schema.
 * @return The drop statements.
 * @throws SQLException when the statements could not be generated.
 */
private List<String> generateDropStatementsForProcedures() throws SQLException {
  String dropProcGenQuery="select SPECIFICNAME from SYSCAT.ROUTINES where ROUTINETYPE='P' and ROUTINESCHEMA = '" + name + "'";
  return buildDropStatements("DROP SPECIFIC PROCEDURE",dropProcGenQuery);
}
