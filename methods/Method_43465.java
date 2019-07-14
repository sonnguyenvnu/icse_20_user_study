static BigDecimal nullSafeSum(BigDecimal a,BigDecimal b){
  return zeroIfNull(a).add(zeroIfNull(b));
}
