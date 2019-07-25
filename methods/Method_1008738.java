/** 
 * Throws SQLException with error code.
 * @param errorCode Error code to be passed.
 * @throws SQLException
 */
public final void throwex(int errorCode) throws SQLException {
  throw newSQLException(errorCode);
}
