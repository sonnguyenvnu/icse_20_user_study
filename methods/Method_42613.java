/** 
 * ??????????
 * @param merchantNo   ????
 * @param merchantName ????
 * @param productName  ????
 * @param orderNo      ?????
 * @param orderPrice   ????
 * @param payWay       ??????
 * @param payWayName   ??????
 * @param payType      ????
 * @param fundIntoType ??????
 * @param feeRate      ????
 * @param orderIp      ??IP
 * @param returnUrl    ??????
 * @param notifyUrl    ??????
 * @param remark       ??
 * @param field1       ????1
 * @param field2       ????2
 * @param field3       ????3
 * @param field4       ????4
 * @param field5       ????5
 * @return
 */
private RpTradePaymentRecord sealRpTradePaymentRecord(String merchantNo,String merchantName,String productName,String orderNo,BigDecimal orderPrice,String payWay,String payWayName,PayTypeEnum payType,String fundIntoType,BigDecimal feeRate,String orderIp,String returnUrl,String notifyUrl,String remark,String field1,String field2,String field3,String field4,String field5){
  RpTradePaymentRecord rpTradePaymentRecord=new RpTradePaymentRecord();
  rpTradePaymentRecord.setProductName(productName);
  rpTradePaymentRecord.setMerchantOrderNo(orderNo);
  String trxNo=buildNoService.buildTrxNo();
  rpTradePaymentRecord.setTrxNo(trxNo);
  String bankOrderNo=buildNoService.buildBankOrderNo();
  rpTradePaymentRecord.setBankOrderNo(bankOrderNo);
  rpTradePaymentRecord.setMerchantName(merchantName);
  rpTradePaymentRecord.setMerchantNo(merchantNo);
  rpTradePaymentRecord.setOrderIp(orderIp);
  rpTradePaymentRecord.setOrderRefererUrl("");
  rpTradePaymentRecord.setReturnUrl(returnUrl);
  rpTradePaymentRecord.setNotifyUrl(notifyUrl);
  rpTradePaymentRecord.setPayWayCode(payWay);
  rpTradePaymentRecord.setPayWayName(payWayName);
  rpTradePaymentRecord.setTrxType(TrxTypeEnum.EXPENSE.name());
  rpTradePaymentRecord.setOrderFrom(OrderFromEnum.USER_EXPENSE.name());
  rpTradePaymentRecord.setOrderAmount(orderPrice);
  rpTradePaymentRecord.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());
  rpTradePaymentRecord.setPayTypeCode(payType.name());
  rpTradePaymentRecord.setPayTypeName(payType.getDesc());
  rpTradePaymentRecord.setFundIntoType(fundIntoType);
  if (FundInfoTypeEnum.PLAT_RECEIVES.name().equals(fundIntoType)) {
    BigDecimal orderAmount=rpTradePaymentRecord.getOrderAmount();
    BigDecimal platIncome=orderAmount.multiply(feeRate).divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_HALF_UP);
    BigDecimal platCost=orderAmount.multiply(BigDecimal.valueOf(Double.valueOf(WeixinConfigUtil.readConfig("pay_rate")))).divide(BigDecimal.valueOf(100),2,BigDecimal.ROUND_HALF_UP);
    BigDecimal platProfit=platIncome.subtract(platCost);
    rpTradePaymentRecord.setFeeRate(feeRate);
    rpTradePaymentRecord.setPlatCost(platCost);
    rpTradePaymentRecord.setPlatIncome(platIncome);
    rpTradePaymentRecord.setPlatProfit(platProfit);
  }
  rpTradePaymentRecord.setRemark(remark);
  rpTradePaymentRecord.setField1(field1);
  rpTradePaymentRecord.setField2(field2);
  rpTradePaymentRecord.setField3(field3);
  rpTradePaymentRecord.setField4(field4);
  rpTradePaymentRecord.setField5(field5);
  return rpTradePaymentRecord;
}
