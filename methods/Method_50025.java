/** 
 * Add a "BCC" value.
 * @param value the value
 * @throws NullPointerException if the value is null.
 */
public void addBcc(EncodedStringValue value){
  mPduHeaders.appendEncodedStringValue(value,PduHeaders.BCC);
}
