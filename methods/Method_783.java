public static BigDecimal castToBigDecimal(Object value){
  if (value == null) {
    return null;
  }
  if (value instanceof BigDecimal) {
    return (BigDecimal)value;
  }
  if (value instanceof BigInteger) {
    return new BigDecimal((BigInteger)value);
  }
  String strVal=value.toString();
  if (strVal.length() == 0) {
    return null;
  }
  if (value instanceof Map && ((Map)value).size() == 0) {
    return null;
  }
  return new BigDecimal(strVal);
}
