/** 
 * Add a "To" value.
 * @param value the value
 * @throws NullPointerException if the value is null.
 */
public void addTo(EncodedStringValue value){
  mPduHeaders.appendEncodedStringValue(value,PduHeaders.TO);
}
