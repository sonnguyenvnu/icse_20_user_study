/** 
 * ??????
 * @param returnType ????
 * @param bidRequestAmount ????
 * @param yearRate ???
 * @param monthIndex ???
 * @param monthes2Return ????
 * @return
 */
public static BigDecimal calMonthToReturnMoney(int returnType,BigDecimal bidRequestAmount,BigDecimal yearRate,int monthIndex,int monthes2Return){
  BigDecimal monthToReturnMoney=BigDecimal.ZERO;
  BigDecimal monthlyRate=getMonthlyRate(yearRate);
  if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL) {
    if (monthes2Return == 1) {
      monthToReturnMoney=bidRequestAmount.add(bidRequestAmount.multiply(monthlyRate)).setScale(BidConst.CAL_SCALE,RoundingMode.HALF_UP);
    }
 else {
      BigDecimal temp1=bidRequestAmount.multiply(monthlyRate);
      BigDecimal temp2=(BigDecimal.ONE.add(monthlyRate)).pow(monthes2Return);
      BigDecimal temp3=(BigDecimal.ONE.add(monthlyRate)).pow(monthes2Return).subtract(BigDecimal.ONE);
      monthToReturnMoney=temp1.multiply(temp2).divide(temp3,BidConst.CAL_SCALE,RoundingMode.HALF_UP);
    }
  }
 else   if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST) {
    BigDecimal monthlyInterest=bidRequestAmount.multiply(monthlyRate).setScale(BidConst.CAL_SCALE,RoundingMode.HALF_UP);
    if (monthIndex == monthes2Return) {
      monthToReturnMoney=bidRequestAmount.add(monthlyInterest).setScale(BidConst.CAL_SCALE,RoundingMode.HALF_UP);
    }
 else     if (monthIndex < monthes2Return) {
      monthToReturnMoney=monthlyInterest;
    }
  }
  return DecimalFormatUtil.formatBigDecimal(monthToReturnMoney,BidConst.STORE_SCALE);
}
