/** 
 * Generate the statements to clean the events in this schema.
 * @return The list of statements.
 * @throws SQLException when the clean statements could not be generated.
 */
private List<String> cleanEvents() throws SQLException {
  List<String> eventNames=jdbcTemplate.queryForStringList("SELECT event_name FROM information_schema.events WHERE event_schema=?",name);
  List<String> statements=new ArrayList<>();
  for (  String eventName : eventNames) {
    statements.add("DROP EVENT " + database.quote(name,eventName));
  }
  return statements;
}
