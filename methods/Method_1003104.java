/** 
 * Returns the length.
 * @return the length
 */
@Override public long length() throws SQLException {
  try {
    debugCodeCall("length");
    checkReadable();
    if (value.getValueType() == Value.BLOB) {
      long precision=value.getType().getPrecision();
      if (precision > 0) {
        return precision;
      }
    }
    return IOUtils.copyAndCloseInput(value.getInputStream(),null);
  }
 catch (  Exception e) {
    throw logAndConvert(e);
  }
}
