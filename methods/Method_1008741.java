/** 
 * @see org.sqlite.core.DB#prepare(java.lang.String)
 */
@Override protected synchronized long prepare(String sql) throws SQLException {
  return prepare_utf8(stringToUtf8ByteArray(sql));
}
