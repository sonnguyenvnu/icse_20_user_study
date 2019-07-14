public static BigDecimal percentToFactor(BigDecimal percent){
  int PERCENT_DECIMAL_SHIFT=2;
  return percent.movePointLeft(PERCENT_DECIMAL_SHIFT);
}
