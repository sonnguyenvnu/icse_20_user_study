/** 
 * ?????
 */
public static BigDecimal getMonthlyRate(BigDecimal yearRate){
  if (yearRate == null)   return BigDecimal.ZERO;
  return yearRate.divide(ONE_HUNDRED).divide(NUMBER_MONTHS_OF_YEAR,BidConst.CAL_SCALE,RoundingMode.HALF_UP);
}
