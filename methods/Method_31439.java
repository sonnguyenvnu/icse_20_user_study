/** 
 * Generates the statements for dropping the domains in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForDomains() throws SQLException {
  List<String> domainNames=jdbcTemplate.queryForStringList("SELECT t.typname as domain_name\n" + "FROM pg_catalog.pg_type t\n" + "       LEFT JOIN pg_catalog.pg_namespace n ON n.oid = t.typnamespace\n" + "       LEFT JOIN pg_depend dep ON dep.objid = t.oid AND dep.deptype = 'e'\n" + "WHERE t.typtype = 'd'\n" + "  AND n.nspname = ?\n" + "  AND dep.objid IS NULL",name);
  List<String> statements=new ArrayList<>();
  for (  String domainName : domainNames) {
    statements.add("DROP DOMAIN " + database.quote(name,domainName));
  }
  return statements;
}
