/** 
 * Set TO, CC or BCC header value.
 * @param value the value
 * @param field the field
 * @return the EncodedStringValue value array of the pdu headerwith specified header field
 * @throws NullPointerException if the value is null.
 */
protected void setEncodedStringValues(EncodedStringValue[] value,int field){
  if (null == value) {
    throw new NullPointerException();
  }
switch (field) {
case BCC:
case CC:
case TO:
    break;
default :
  throw new RuntimeException("Invalid header field!");
}
ArrayList<EncodedStringValue> list=new ArrayList<EncodedStringValue>();
for (int i=0; i < value.length; i++) {
list.add(value[i]);
}
mHeaderMap.put(field,list);
}
