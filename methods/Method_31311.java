/** 
 * Generate the statements for dropping all the constraints in this schema.
 * @return The list of statements.
 * @throws SQLException when the statements could not be generated.
 */
private List<String> generateDropStatementsForConstraints() throws SQLException {
  List<Map<String,String>> results=jdbcTemplate.queryForList("SELECT c.constraintname, t.tablename FROM sys.sysconstraints c" + " INNER JOIN sys.systables t ON c.tableid = t.tableid" + " INNER JOIN sys.sysschemas s ON c.schemaid = s.schemaid" + " WHERE c.type = 'F' AND s.schemaname = ?",name);
  List<String> statements=new ArrayList<>();
  for (  Map<String,String> result : results) {
    String dropStatement="ALTER TABLE " + database.quote(name,result.get("TABLENAME")) + " DROP CONSTRAINT " + database.quote(result.get("CONSTRAINTNAME"));
    statements.add(dropStatement);
  }
  return statements;
}
