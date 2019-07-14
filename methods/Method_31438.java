/** 
 * Generates the statements for dropping the enums in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> generateDropStatementsForEnums() throws SQLException {
  List<String> enumNames=jdbcTemplate.queryForStringList("SELECT t.typname FROM pg_catalog.pg_type t INNER JOIN pg_catalog.pg_namespace n ON n.oid = t.typnamespace WHERE n.nspname = ? and t.typtype = 'e'",name);
  List<String> statements=new ArrayList<>();
  for (  String enumName : enumNames) {
    statements.add("DROP TYPE " + database.quote(name,enumName));
  }
  return statements;
}
