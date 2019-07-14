@SuppressWarnings("rawtypes") static boolean eqNotNull(Number a,Number b){
  Class clazzA=a.getClass();
  boolean isIntA=isInt(clazzA);
  Class clazzB=b.getClass();
  boolean isIntB=isInt(clazzB);
  if (a instanceof BigDecimal) {
    BigDecimal decimalA=(BigDecimal)a;
    if (isIntB) {
      return decimalA.equals(BigDecimal.valueOf(TypeUtils.longExtractValue(b)));
    }
  }
  if (isIntA) {
    if (isIntB) {
      return a.longValue() == b.longValue();
    }
    if (b instanceof BigInteger) {
      BigInteger bigIntB=(BigInteger)a;
      BigInteger bigIntA=BigInteger.valueOf(a.longValue());
      return bigIntA.equals(bigIntB);
    }
  }
  if (isIntB) {
    if (a instanceof BigInteger) {
      BigInteger bigIntA=(BigInteger)a;
      BigInteger bigIntB=BigInteger.valueOf(TypeUtils.longExtractValue(b));
      return bigIntA.equals(bigIntB);
    }
  }
  boolean isDoubleA=isDouble(clazzA);
  boolean isDoubleB=isDouble(clazzB);
  if ((isDoubleA && isDoubleB) || (isDoubleA && isIntB) || (isDoubleB && isIntA)) {
    return a.doubleValue() == b.doubleValue();
  }
  return false;
}
