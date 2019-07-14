/** 
 * ???????
 * @param returnType ????
 * @param bidRequestAmount ????
 * @param yearRate ???
 * @param monthes2Return ????
 * @return
 */
public static BigDecimal calTotalInterest(int returnType,BigDecimal bidRequestAmount,BigDecimal yearRate,int monthes2Return){
  BigDecimal totalInterest=BigDecimal.ZERO;
  BigDecimal monthlyRate=getMonthlyRate(yearRate);
  if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL) {
    if (monthes2Return == 1) {
      totalInterest=bidRequestAmount.multiply(monthlyRate).setScale(BidConst.CAL_SCALE,RoundingMode.HALF_UP);
    }
 else {
      BigDecimal temp1=bidRequestAmount.multiply(monthlyRate);
      BigDecimal temp2=(BigDecimal.ONE.add(monthlyRate)).pow(monthes2Return);
      BigDecimal temp3=(BigDecimal.ONE.add(monthlyRate)).pow(monthes2Return).subtract(BigDecimal.ONE);
      BigDecimal monthToReturnMoney=temp1.multiply(temp2).divide(temp3,BidConst.CAL_SCALE,RoundingMode.HALF_UP);
      BigDecimal totalReturnMoney=monthToReturnMoney.multiply(new BigDecimal(monthes2Return));
      totalInterest=totalReturnMoney.subtract(bidRequestAmount);
    }
  }
 else   if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST) {
    BigDecimal monthlyInterest=DecimalFormatUtil.amountFormat(bidRequestAmount.multiply(monthlyRate));
    totalInterest=monthlyInterest.multiply(new BigDecimal(monthes2Return));
  }
  return DecimalFormatUtil.formatBigDecimal(totalInterest,BidConst.STORE_SCALE);
}
