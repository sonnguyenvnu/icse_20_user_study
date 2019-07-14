/** 
 * Generates the statements for dropping the materialized views in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForMaterializedViews() throws SQLException {
  List<String> viewNames=jdbcTemplate.queryForStringList("SELECT relname FROM pg_catalog.pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace" + " WHERE c.relkind = 'm' AND n.nspname = ?",name);
  List<String> statements=new ArrayList<>();
  for (  String domainName : viewNames) {
    statements.add("DROP MATERIALIZED VIEW IF EXISTS " + database.quote(name,domainName) + " CASCADE");
  }
  return statements;
}
