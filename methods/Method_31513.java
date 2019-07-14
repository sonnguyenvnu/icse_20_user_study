/** 
 * Cleans the CLR assemblies in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanAssemblies() throws SQLException {
  List<String> assemblyNames=jdbcTemplate.queryForStringList("SELECT * FROM sys.assemblies WHERE is_user_defined=1");
  List<String> statements=new ArrayList<>();
  for (  String assemblyName : assemblyNames) {
    statements.add("DROP ASSEMBLY " + database.quote(assemblyName));
  }
  return statements;
}
