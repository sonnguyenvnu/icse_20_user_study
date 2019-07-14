/** 
 * Generates the statements for dropping the routines in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForRoutines() throws SQLException {
  String isAggregate=database.getVersion().isAtLeast("11") ? "pg_proc.prokind = 'a'" : "pg_proc.proisagg";
  List<Map<String,String>> rows=jdbcTemplate.queryForList("SELECT proname, oidvectortypes(proargtypes) AS args, " + isAggregate + " as agg " + "FROM pg_proc INNER JOIN pg_namespace ns ON (pg_proc.pronamespace = ns.oid) " + "LEFT JOIN pg_depend dep ON dep.objid = pg_proc.oid AND dep.deptype = 'e' " + "WHERE ns.nspname = ? AND dep.objid IS NULL",name);
  List<String> statements=new ArrayList<>();
  for (  Map<String,String> row : rows) {
    String type=isTrue(row.get("agg")) ? "AGGREGATE" : "FUNCTION";
    statements.add("DROP " + type + " IF EXISTS " + database.quote(name,row.get("proname")) + "(" + row.get("args") + ") CASCADE");
  }
  return statements;
}
