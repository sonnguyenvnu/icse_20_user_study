/** 
 * Read one bit.
 * @return true if 1, false if 0.
 * @throws JSONException
 */
private boolean bit() throws JSONException {
  boolean value;
  try {
    value=this.bitreader.bit();
    if (probe) {
      log(value ? 1 : 0);
    }
    return value;
  }
 catch (  Throwable e) {
    throw new JSONException(e);
  }
}
