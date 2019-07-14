/** 
 * Generates DROP statements for this type of table, representing this type of object in this schema.
 * @param tableType  The type of table (Can be T, V, S, ...).
 * @param objectType The type of object.
 * @return The drop statements.
 * @throws SQLException when the statements could not be generated.
 */
private List<String> generateDropStatements(String tableType,String objectType) throws SQLException {
  String dropTablesGenQuery="select TABNAME from SYSCAT.TABLES where TYPE='" + tableType + "' and TABSCHEMA = '" + name + "'";
  return buildDropStatements("DROP " + objectType,dropTablesGenQuery);
}
