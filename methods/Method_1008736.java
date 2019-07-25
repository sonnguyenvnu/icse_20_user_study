/** 
 * Executes the given SQL statement using the one-step query execution interface.
 * @param sql SQL statement to be executed.
 * @return True if a row of ResultSet is ready; false otherwise.
 * @throws SQLException
 * @see <a href="http://www.sqlite.org/c3ref/exec.html">http://www.sqlite.org/c3ref/exec.html</a>
 */
final synchronized boolean execute(String sql,boolean autoCommit) throws SQLException {
  int statusCode=_exec(sql);
switch (statusCode) {
case SQLITE_OK:
    return false;
case SQLITE_DONE:
  ensureAutoCommit(autoCommit);
return false;
case SQLITE_ROW:
return true;
default :
throw newSQLException(statusCode);
}
}
