/** 
 * ??????
 * @param returnType ????
 * @param bidRequestAmount ????
 * @param yearRate ???
 * @param monthIndex ???
 * @param monthes2Return ????
 * @return
 */
public static BigDecimal calMonthlyInterest(int returnType,BigDecimal bidRequestAmount,BigDecimal yearRate,int monthIndex,int monthes2Return){
  BigDecimal monthlyInterest=BigDecimal.ZERO;
  BigDecimal monthlyRate=getMonthlyRate(yearRate);
  if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL) {
    if (monthes2Return == 1) {
      monthlyInterest=bidRequestAmount.multiply(monthlyRate).setScale(BidConst.CAL_SCALE,RoundingMode.HALF_UP);
    }
 else {
      BigDecimal temp1=bidRequestAmount.multiply(monthlyRate);
      BigDecimal temp2=(BigDecimal.ONE.add(monthlyRate)).pow(monthes2Return);
      BigDecimal temp3=(BigDecimal.ONE.add(monthlyRate)).pow(monthes2Return).subtract(BigDecimal.ONE);
      BigDecimal temp4=(BigDecimal.ONE.add(monthlyRate)).pow(monthIndex - 1);
      BigDecimal monthToReturnMoney=temp1.multiply(temp2).divide(temp3,BidConst.CAL_SCALE,RoundingMode.HALF_UP);
      BigDecimal totalReturnMoney=monthToReturnMoney.multiply(new BigDecimal(monthes2Return));
      BigDecimal totalInterest=totalReturnMoney.subtract(bidRequestAmount);
      if (monthIndex < monthes2Return) {
        monthlyInterest=(temp1.subtract(monthToReturnMoney)).multiply(temp4).add(monthToReturnMoney).setScale(BidConst.CAL_SCALE,RoundingMode.HALF_UP);
      }
 else       if (monthIndex == monthes2Return) {
        BigDecimal temp6=BigDecimal.ZERO;
        for (int i=1; i < monthes2Return; i++) {
          BigDecimal temp5=(BigDecimal.ONE.add(monthlyRate)).pow(i - 1);
          monthlyInterest=(temp1.subtract(monthToReturnMoney)).multiply(temp5).add(monthToReturnMoney).setScale(BidConst.CAL_SCALE,RoundingMode.HALF_UP);
          temp6=temp6.add(monthlyInterest);
        }
        monthlyInterest=totalInterest.subtract(temp6);
      }
    }
  }
 else   if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST) {
    monthlyInterest=DecimalFormatUtil.amountFormat(bidRequestAmount.multiply(monthlyRate));
  }
  return monthlyInterest;
}
