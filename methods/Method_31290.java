/** 
 * Generates DROP statements for the views in this schema.
 * @return The drop statements.
 * @throws SQLException when the statements could not be generated.
 */
private List<String> generateDropStatementsForViews() throws SQLException {
  String dropSeqGenQuery="select TABNAME from SYSCAT.TABLES where TYPE='V' AND TABSCHEMA = '" + name + "'" + " and substr(property,19,1) <> 'Y'";
  return buildDropStatements("DROP VIEW",dropSeqGenQuery);
}
