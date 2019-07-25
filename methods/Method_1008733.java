/** 
 * Complies the an SQL statement.
 * @param stmt The SQL statement to compile.
 * @throws SQLException
 * @see <a href="http://www.sqlite.org/c3ref/prepare.html">http://www.sqlite.org/c3ref/prepare.html</a>
 */
public final synchronized void prepare(CoreStatement stmt) throws SQLException {
  if (stmt.sql == null) {
    throw new NullPointerException();
  }
  if (stmt.pointer != 0) {
    finalize(stmt);
  }
  stmt.pointer=prepare(stmt.sql);
  stmts.put(new Long(stmt.pointer),stmt);
}
