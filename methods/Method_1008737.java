/** 
 * Throws SQLException with error message.
 * @throws SQLException
 */
final void throwex() throws SQLException {
  throw new SQLException(errmsg());
}
