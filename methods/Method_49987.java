/** 
 * Set TextString value to pdu header by header field.
 * @param value the value
 * @param field the field
 * @return the TextString value of the pdu headerwith specified header field
 * @throws NullPointerException if the value is null.
 */
protected void setTextString(byte[] value,int field){
  if (null == value) {
    throw new NullPointerException();
  }
switch (field) {
case TRANSACTION_ID:
case REPLY_CHARGING_ID:
case AUX_APPLIC_ID:
case APPLIC_ID:
case REPLY_APPLIC_ID:
case MESSAGE_ID:
case REPLACE_ID:
case CANCEL_ID:
case CONTENT_LOCATION:
case MESSAGE_CLASS:
case CONTENT_TYPE:
    break;
default :
  throw new RuntimeException("Invalid header field!");
}
mHeaderMap.put(field,value);
}
