/** 
 * Checks whether the specified query returns rows or not. Wraps the query in EXISTS() SQL function and executes it. This is more preferable to opening a cursor for the original query, because a query inside EXISTS() is implicitly optimized to return the first row and because the client never fetches more than 1 row despite the fetch size value.
 * @param query  The query to check.
 * @param params The query parameters.
 * @return {@code true} if the query returns rows, {@code false} if not.
 * @throws SQLException when the query execution failed.
 */
boolean queryReturnsRows(String query,String... params) throws SQLException {
  return getMainConnection().getJdbcTemplate().queryForBoolean("SELECT CASE WHEN EXISTS(" + query + ") THEN 1 ELSE 0 END FROM DUAL",params);
}
