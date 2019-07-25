/** 
 * Throws an SQLException
 * @param msg Message for the SQLException.
 * @throws SQLException
 */
static void throwex(String msg) throws SQLException {
  throw new SQLException(msg);
}
