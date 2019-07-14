/** 
 * @return all table names in the current database.
 */
private List<String> retrieveAllTableNames() throws SQLException {
  return jdbcTemplate.queryForStringList("select ob.name from sysobjects ob where ob.type=? order by ob.name","U");
}
