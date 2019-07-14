/** 
 * Generate the statements to clean the routines in this schema.
 * @return The list of statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanRoutines() throws SQLException {
  List<Map<String,String>> routineNames=jdbcTemplate.queryForList("SELECT routine_name as 'N', routine_type as 'T' FROM information_schema.routines WHERE routine_schema=?",name);
  List<String> statements=new ArrayList<>();
  for (  Map<String,String> row : routineNames) {
    String routineName=row.get("N");
    String routineType=row.get("T");
    statements.add("DROP " + routineType + " " + database.quote(name,routineName));
  }
  return statements;
}
