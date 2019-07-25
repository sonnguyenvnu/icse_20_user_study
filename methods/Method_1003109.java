/** 
 * [Not supported] Searches a pattern and return the position.
 */
@Override public long position(String pattern,long start) throws SQLException {
  throw unsupported("LOB search");
}
