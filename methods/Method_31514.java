/** 
 * Cleans the triggers in this schema.
 * @return The drop statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanTriggers() throws SQLException {
  List<String> triggerNames=jdbcTemplate.queryForStringList("SELECT * FROM sys.triggers" + " WHERE is_ms_shipped=0 AND parent_id=0 AND parent_class_desc='DATABASE'");
  List<String> statements=new ArrayList<>();
  for (  String triggerName : triggerNames) {
    statements.add("DROP TRIGGER " + database.quote(triggerName) + " ON DATABASE");
  }
  return statements;
}
