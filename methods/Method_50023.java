/** 
 * Add a "CC" value.
 * @param value the value
 * @throws NullPointerException if the value is null.
 */
public void addCc(EncodedStringValue value){
  mPduHeaders.appendEncodedStringValue(value,PduHeaders.CC);
}
