@SuppressWarnings({"unchecked","rawtypes"}) private static Amount<?,?> create(Type valueType,Number number,Unit unit){
  if (valueType == Integer.class) {
    return Amount.of(number.intValue(),unit);
  }
 else   if (valueType == Double.class) {
    return Amount.of(number.doubleValue(),unit);
  }
 else   if (valueType == Long.class) {
    return Amount.of(number.longValue(),unit);
  }
 else   if (valueType == Byte.class) {
    return Amount.of(number.byteValue(),unit);
  }
 else   if (valueType == Short.class) {
    return Amount.of(number.shortValue(),unit);
  }
 else   if (valueType == Float.class) {
    return Amount.of(number.floatValue(),unit);
  }
  throw new IllegalArgumentException("Unrecognized number class " + valueType);
}
