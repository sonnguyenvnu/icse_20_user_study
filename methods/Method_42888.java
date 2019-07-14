public static BigDecimal percentToFactor(BigDecimal percent){
  return percent.movePointLeft(PERCENT_DECIMAL_SHIFT);
}
