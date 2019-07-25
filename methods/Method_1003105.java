/** 
 * [Not supported] Truncates the object.
 * @param len the new length
 */
@Override public void truncate(long len) throws SQLException {
  throw unsupported("LOB update");
}
