/** 
 * ?????????????=????/???? *???
 * @param bidRequestAmount ????
 * @param monthes2Return ????
 * @param yearRate ???
 * @param returnType ????
 * @param acturalBidAmount ????
 * @return
 */
public static BigDecimal calBidInterest(BigDecimal bidRequestAmount,int monthes2Return,BigDecimal yearRate,int returnType,BigDecimal acturalBidAmount){
  BigDecimal totalInterest=calTotalInterest(returnType,bidRequestAmount,yearRate,monthes2Return);
  BigDecimal proportion=acturalBidAmount.divide(bidRequestAmount,BidConst.CAL_SCALE,RoundingMode.HALF_UP);
  BigDecimal bidInterest=totalInterest.multiply(proportion);
  return DecimalFormatUtil.formatBigDecimal(bidInterest,BidConst.STORE_SCALE);
}
