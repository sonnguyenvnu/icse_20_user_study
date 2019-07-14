public static byte byteValue(BigDecimal decimal){
  if (decimal == null) {
    return 0;
  }
  int scale=decimal.scale();
  if (scale >= -100 && scale <= 100) {
    return decimal.byteValue();
  }
  return decimal.byteValueExact();
}
