/** 
 * Bind values to prepared statements
 * @param stmt Pointer to the statement.
 * @param pos Index of the SQL parameter to be set to NULL.
 * @param v Value to bind to the parameter.
 * @return <a href="http://www.sqlite.org/c3ref/c_abort.html">Result Codes</a>
 * @throws SQLException
 * @see <a href="http://www.sqlite.org/c3ref/bind_blob.html">http://www.sqlite.org/c3ref/bind_blob.html</a>
 */
final synchronized int sqlbind(long stmt,int pos,Object v) throws SQLException {
  pos++;
  if (v == null) {
    return bind_null(stmt,pos);
  }
 else   if (v instanceof Integer) {
    return bind_int(stmt,pos,((Integer)v).intValue());
  }
 else   if (v instanceof Short) {
    return bind_int(stmt,pos,((Short)v).intValue());
  }
 else   if (v instanceof Long) {
    return bind_long(stmt,pos,((Long)v).longValue());
  }
 else   if (v instanceof Float) {
    return bind_double(stmt,pos,((Float)v).doubleValue());
  }
 else   if (v instanceof Double) {
    return bind_double(stmt,pos,((Double)v).doubleValue());
  }
 else   if (v instanceof String) {
    return bind_text(stmt,pos,(String)v);
  }
 else   if (v instanceof byte[]) {
    return bind_blob(stmt,pos,(byte[])v);
  }
 else {
    throw new SQLException("unexpected param type: " + v.getClass());
  }
}
