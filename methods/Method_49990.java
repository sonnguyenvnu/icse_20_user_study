/** 
 * Set LongInteger value to pdu header by header field.
 * @param value the value
 * @param field the field
 */
protected void setLongInteger(long value,int field){
switch (field) {
case DATE:
case REPLY_CHARGING_SIZE:
case MESSAGE_SIZE:
case MESSAGE_COUNT:
case START:
case LIMIT:
case DELIVERY_TIME:
case EXPIRY:
case REPLY_CHARGING_DEADLINE:
case PREVIOUSLY_SENT_DATE:
    break;
default :
  throw new RuntimeException("Invalid header field!");
}
mHeaderMap.put(field,value);
}
