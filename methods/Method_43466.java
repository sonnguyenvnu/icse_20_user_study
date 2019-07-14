static BigDecimal zeroIfNull(BigDecimal a){
  return a == null ? BigDecimal.ZERO : a;
}
