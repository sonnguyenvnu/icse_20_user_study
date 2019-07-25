/** 
 * Returns the character position at which the specified substring searchstr appears in the SQL CLOB value represented by this Clob object. The search begins at position start.
 * @param searchstr - the substring for which to search
 * @param start - the position at which to begin searching; the first position is 1
 * @return the position at which the substring appears or -1 if it is not present; the first position is 1
 * @throws SQLException - if there is an error accessing the CLOB value or if start is less than 1
 */
public long position(String searchstr,long start) throws SQLException {
  checkClosed();
  getStringFromStream();
  if (start < 1) {
    MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_invalidPositionIndex"));
    Object[] msgArgs={start};
    SQLServerException.makeFromDriverError(con,null,form.format(msgArgs),null,true);
  }
  if (null == searchstr)   return -1;
  int pos=value.indexOf(searchstr,(int)(start - 1));
  if (-1 != pos)   return pos + 1L;
  return -1;
}
