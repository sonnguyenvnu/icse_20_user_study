/** 
 * Returns the set of Oracle options available on the target database.
 * @return the set of option titles.
 * @throws SQLException if retrieving of options failed.
 */
private Set<String> getAvailableOptions() throws SQLException {
  return new HashSet<>(getMainConnection().getJdbcTemplate().queryForStringList("SELECT PARAMETER FROM V$OPTION WHERE VALUE = 'TRUE'"));
}
