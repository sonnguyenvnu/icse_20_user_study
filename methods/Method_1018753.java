/** 
 * Returns the character position at which the specified Clob object searchstr appears in this Clob object. The search begins at position start.
 * @param searchstr - the Clob for which to search
 * @param start - the position at which to begin searching; the first position is 1
 * @return the position at which the Clob object appears or -1 if it is not present; the first position is 1
 * @throws SQLException - if there is an error accessing the CLOB value or if start is less than 1
 */
public long position(Clob searchstr,long start) throws SQLException {
  checkClosed();
  getStringFromStream();
  if (start < 1) {
    MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_invalidPositionIndex"));
    Object[] msgArgs={start};
    SQLServerException.makeFromDriverError(con,null,form.format(msgArgs),null,true);
  }
  if (null == searchstr)   return -1;
  return position(searchstr.getSubString(1,(int)searchstr.length()),start);
}
