/** 
 * Allow to re-use the prepared statement.
 * @param sql the SQL statement
 * @param prep the prepared statement
 */
void reuse(String sql,PreparedStatement prep){
  assert Thread.holdsLock(database);
  prepared.put(sql,prep);
}
