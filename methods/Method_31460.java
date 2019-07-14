/** 
 * Generates the statements for dropping the routines in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForRoutines() throws SQLException {
  List<Map<String,String>> rows=jdbcTemplate.queryForList("SELECT proname, oidvectortypes(proargtypes) AS args " + "FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid) " + "LEFT JOIN pg_depend dep ON dep.objid = pg_proc.oid AND dep.deptype = 'e' " + "WHERE pg_proc.proisagg = false AND ns.nspname = ? AND dep.objid IS NULL",name);
  List<String> statements=new ArrayList<>();
  for (  Map<String,String> row : rows) {
    statements.add("DROP FUNCTION " + database.quote(name,row.get("proname")) + "(" + row.get("args") + ") CASCADE");
  }
  return statements;
}
