/** 
 * Gets the Saxon representation of the parameter, if its type corresponds  to an XPath 2.0 atomic datatype.
 * @param value The value to convert
 * @return The converted AtomicValue
 */
public static AtomicValue getAtomicRepresentation(final Object value){
  if (value == null) {
    return UntypedAtomicValue.ZERO_LENGTH_UNTYPED;
  }
 else   if (value instanceof Enum) {
    return new StringValue(value.toString());
  }
 else   if (value instanceof String) {
    return new StringValue((String)value);
  }
 else   if (value instanceof Boolean) {
    return BooleanValue.get((Boolean)value);
  }
 else   if (value instanceof Integer) {
    return Int64Value.makeIntegerValue((Integer)value);
  }
 else   if (value instanceof Long) {
    return new BigIntegerValue((Long)value);
  }
 else   if (value instanceof Double) {
    return new DoubleValue((Double)value);
  }
 else   if (value instanceof Character) {
    return new StringValue(value.toString());
  }
 else   if (value instanceof Float) {
    return new FloatValue((Float)value);
  }
 else   if (value instanceof Pattern) {
    return new StringValue(String.valueOf(value));
  }
 else {
    throw new RuntimeException("Unable to create ValueRepresentation for value of type: " + value.getClass());
  }
}
