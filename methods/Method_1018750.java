@Override public void truncate(long len) throws SQLException {
  checkClosed();
  getBytesFromStream();
  if (len < 0) {
    MessageFormat form=new MessageFormat(SQLServerException.getErrString(R_INVALID_LENGTH));
    Object[] msgArgs={len};
    SQLServerException.makeFromDriverError(con,null,form.format(msgArgs),null,true);
  }
  if (value.length > len) {
    byte[] bNew=new byte[(int)len];
    System.arraycopy(value,0,bNew,0,(int)len);
    value=bNew;
  }
}
