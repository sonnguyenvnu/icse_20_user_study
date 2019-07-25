/** 
 * Truncates the CLOB value that this Clob designates to have a length of len characters.
 * @param len the length, in characters, to which the CLOB value should be truncated
 * @throws SQLException when an error occurs
 */
public void truncate(long len) throws SQLException {
  checkClosed();
  getStringFromStream();
  if (len < 0) {
    MessageFormat form=new MessageFormat(SQLServerException.getErrString("R_invalidLength"));
    Object[] msgArgs={len};
    SQLServerException.makeFromDriverError(con,null,form.format(msgArgs),null,true);
  }
  if (len <= Integer.MAX_VALUE && value.length() > len)   value=value.substring(0,(int)len);
}
