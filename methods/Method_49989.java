/** 
 * Append one EncodedStringValue to another.
 * @param value the EncodedStringValue to append
 * @param field the field
 * @throws NullPointerException if the value is null.
 */
protected void appendEncodedStringValue(EncodedStringValue value,int field){
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
ArrayList<EncodedStringValue> list=(ArrayList<EncodedStringValue>)mHeaderMap.get(field);
if (null == list) {
list=new ArrayList<EncodedStringValue>();
}
list.add(value);
mHeaderMap.put(field,list);
}
