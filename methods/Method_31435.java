/** 
 * Generates the statements for dropping the types in this schema.
 * @param recreate Flag indicating whether the types should be recreated. Necessary for type-function chicken and egg problem.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForBaseTypes(boolean recreate) throws SQLException {
  List<Map<String,String>> rows=jdbcTemplate.queryForList("select typname, typcategory from pg_catalog.pg_type t " + "left join pg_depend dep on dep.objid = t.oid and dep.deptype = 'e' " + "where (t.typrelid = 0 OR (SELECT c.relkind = 'c' FROM pg_catalog.pg_class c WHERE c.oid = t.typrelid)) " + "and NOT EXISTS(SELECT 1 FROM pg_catalog.pg_type el WHERE el.oid = t.typelem AND el.typarray = t.oid) " + "and t.typnamespace in (select oid from pg_catalog.pg_namespace where nspname = ?) " + "and dep.objid is null " + "and t.typtype != 'd'",name);
  List<String> statements=new ArrayList<>();
  for (  Map<String,String> row : rows) {
    statements.add("DROP TYPE IF EXISTS " + database.quote(name,row.get("typname")) + " CASCADE");
  }
  if (recreate) {
    for (    Map<String,String> row : rows) {
      if (Arrays.asList("P","U").contains(row.get("typcategory"))) {
        statements.add("CREATE TYPE " + database.quote(name,row.get("typname")));
      }
    }
  }
  return statements;
}
