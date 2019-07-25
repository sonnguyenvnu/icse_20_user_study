/** 
 * Throws SQL Exception with error code and message.
 * @param errorCode Error code to be passed.
 * @param errorMessage Error message to be passed.
 * @throws SQLException
 */
static final void throwex(int errorCode,String errorMessage) throws SQLiteException {
  throw newSQLException(errorCode,errorMessage);
}
