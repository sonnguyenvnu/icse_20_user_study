/** 
 * Generates the statements for dropping the views in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForViews() throws SQLException {
  List<String> viewNames=jdbcTemplate.queryForStringList("SELECT relname FROM pg_catalog.pg_class c JOIN pg_namespace n ON n.oid = c.relnamespace" + " LEFT JOIN pg_depend dep ON dep.objid = c.oid AND dep.deptype = 'e'" + " WHERE c.relkind = 'v' AND  n.nspname = ? AND dep.objid IS NULL",name);
  List<String> statements=new ArrayList<>();
  for (  String domainName : viewNames) {
    statements.add("DROP VIEW IF EXISTS " + database.quote(name,domainName) + " CASCADE");
  }
  return statements;
}
