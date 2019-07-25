/** 
 * Get a SQL exception meaning this feature is not supported.
 * @param message the message
 * @return the SQL exception
 */
protected SQLException unsupported(String message){
  try {
    throw DbException.getUnsupportedException(message);
  }
 catch (  Exception e) {
    return logAndConvert(e);
  }
}
