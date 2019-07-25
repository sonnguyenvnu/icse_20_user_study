/** 
 * @return Compile-time library version numbers.
 * @throws SQLException
 * @see <a href="http://www.sqlite.org/c3ref/c_source_id.html">http://www.sqlite.org/c3ref/c_source_id.html</a>
 */
public String libversion() throws SQLException {
  checkOpen();
  return db.libversion();
}
