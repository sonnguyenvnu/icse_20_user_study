private static int numberOfDecimals(BigDecimal value){
  double d=value.doubleValue();
  return -(int)Math.round(Math.log10(d));
}
