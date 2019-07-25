/** 
 * [Not supported] Truncates the object.
 */
@Override public void truncate(long len) throws SQLException {
  throw unsupported("LOB update");
}
