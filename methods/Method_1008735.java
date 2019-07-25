/** 
 * @see <a href="http://www.sqlite.org/c_interface.html#sqlite_exec">http://www.sqlite.org/c_interface.html#sqlite_exec</a>
 * @param stmt Stmt object.
 * @param vals Array of parameter values.
 * @return True if a row of ResultSet is ready; false otherwise.
 * @throws SQLException
 */
public final synchronized boolean execute(CoreStatement stmt,Object[] vals) throws SQLException {
  if (vals != null) {
    final int params=bind_parameter_count(stmt.pointer);
    if (params > vals.length) {
      throw new SQLException("assertion failure: param count (" + params + ") > value count (" + vals.length + ")");
    }
    for (int i=0; i < params; i++) {
      int rc=sqlbind(stmt.pointer,i,vals[i]);
      if (rc != SQLITE_OK) {
        throwex(rc);
      }
    }
  }
  int statusCode=step(stmt.pointer) & 0xFF;
switch (statusCode) {
case SQLITE_DONE:
    reset(stmt.pointer);
  ensureAutoCommit(stmt.conn.getAutoCommit());
return false;
case SQLITE_ROW:
return true;
case SQLITE_BUSY:
case SQLITE_LOCKED:
case SQLITE_MISUSE:
case SQLITE_CONSTRAINT:
throw newSQLException(statusCode);
default :
finalize(stmt);
throw newSQLException(statusCode);
}
}
