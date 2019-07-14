/** 
 * Converts a value into a protobuf  {@link Value} object. 
 */
private static Value value(Object value){
  if (value instanceof Map) {
    @SuppressWarnings("unchecked") Map<String,Object> map=(Map<String,Object>)value;
    return Value.newBuilder().setStructValue(jsonToStruct(map)).build();
  }
 else   if (value instanceof Value) {
    return (Value)value;
  }
 else   if (value instanceof String) {
    return Value.newBuilder().setStringValue((String)value).build();
  }
 else   if (value instanceof Boolean) {
    return Value.newBuilder().setBoolValue((Boolean)value).build();
  }
 else   if (value instanceof Number) {
    return Value.newBuilder().setNumberValue(((Number)value).doubleValue()).build();
  }
 else   if (value instanceof Iterable<?>) {
    return listValue((Iterable<?>)value);
  }
 else   if (value instanceof Struct) {
    return Value.newBuilder().setStructValue((Struct)value).build();
  }
 else   if (value == null) {
    return Value.newBuilder().setNullValue(NullValue.NULL_VALUE).build();
  }
 else {
    throw new IllegalArgumentException("Cannot convert " + value + " to a protobuf `Value`");
  }
}
