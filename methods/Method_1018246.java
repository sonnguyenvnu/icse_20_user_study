/** 
 * Write a number, using the number of bits necessary to hold the number.
 * @param integer The value to be encoded.
 * @param width The number of bits to encode the value, between 0 and 32.
 * @throws JSONException
 */
private void write(int integer,int width) throws JSONException {
  try {
    this.bitwriter.write(integer,width);
    if (probe) {
      log(integer,width);
    }
  }
 catch (  Throwable e) {
    throw new JSONException(e);
  }
}
