public static BigInteger castToBigInteger(Object value){
  if (value == null) {
    return null;
  }
  if (value instanceof BigInteger) {
    return (BigInteger)value;
  }
  if (value instanceof Float || value instanceof Double) {
    return BigInteger.valueOf(((Number)value).longValue());
  }
  if (value instanceof BigDecimal) {
    BigDecimal decimal=(BigDecimal)value;
    int scale=decimal.scale();
    if (scale > -1000 && scale < 1000) {
      return ((BigDecimal)value).toBigInteger();
    }
  }
  String strVal=value.toString();
  if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
    return null;
  }
  return new BigInteger(strVal);
}
