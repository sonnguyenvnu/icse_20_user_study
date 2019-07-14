public static BigDecimal monthRateFormat(BigDecimal number){
  return number.multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(12),BidConst.CAL_SCALE,RoundingMode.HALF_UP);
}
