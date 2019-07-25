/** 
 * Returns the length.
 * @return the length
 */
@Override public long length() throws SQLException {
  try {
    debugCodeCall("length");
    checkReadable();
    if (value.getValueType() == Value.CLOB) {
      long precision=value.getType().getPrecision();
      if (precision > 0) {
        return precision;
      }
    }
    return IOUtils.copyAndCloseInput(value.getReader(),null,Long.MAX_VALUE);
  }
 catch (  Exception e) {
    throw logAndConvert(e);
  }
}
