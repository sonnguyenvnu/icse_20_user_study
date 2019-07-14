/** 
 * Converts a string to a number using the narrowest possible type. Possible  returns for this function are BigDecimal, Double, BigInteger, Long, and Integer. When a Double is returned, it should always be a valid Double and not NaN or +-infinity.
 * @param val value to convert
 * @return Number representation of the value.
 * @throws NumberFormatException thrown if the value is not a valid number. A publiccaller should catch this and wrap it in a  {@link JSONException} if applicable.
 */
protected static Number stringToNumber(final String val) throws NumberFormatException {
  char initial=val.charAt(0);
  if ((initial >= '0' && initial <= '9') || initial == '-') {
    if (isDecimalNotation(val)) {
      if (val.length() > 14) {
        return new BigDecimal(val);
      }
      final Double d=Double.valueOf(val);
      if (d.isInfinite() || d.isNaN()) {
        return new BigDecimal(val);
      }
      return d;
    }
    BigInteger bi=new BigInteger(val);
    if (bi.bitLength() <= 31) {
      return Integer.valueOf(bi.intValue());
    }
    if (bi.bitLength() <= 63) {
      return Long.valueOf(bi.longValue());
    }
    return bi;
  }
  throw new NumberFormatException("val [" + val + "] is not a valid number.");
}
