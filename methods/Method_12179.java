/** 
 * ???????
 * @param bidRequestAmount ????
 * @param returnType ????
 * @param monthes2Return ????
 * @return
 */
public static BigDecimal calAccountManagementCharge(BigDecimal bidRequestAmount){
  BigDecimal accountManagementCharge=DecimalFormatUtil.formatBigDecimal(bidRequestAmount.multiply(BidConst.ACCOUNT_MANAGER_CHARGE_RATE),BidConst.CAL_SCALE);
  return accountManagementCharge;
}
