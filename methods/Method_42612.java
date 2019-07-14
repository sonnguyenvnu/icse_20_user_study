/** 
 * ????????
 * @param merchantNo   ????
 * @param merchantName ????
 * @param productName  ????
 * @param orderNo      ?????
 * @param orderDate    ????
 * @param orderTime    ????
 * @param orderPrice   ????
 * @param payWay       ????
 * @param payWayName   ??????
 * @param payType      ????
 * @param fundIntoType ??????
 * @param orderIp      ??IP
 * @param orderPeriod  ?????
 * @param returnUrl    ??????
 * @param notifyUrl    ??????
 * @param remark       ????
 * @param field1       ????1
 * @param field2       ????2
 * @param field3       ????3
 * @param field4       ????4
 * @param field5       ????5
 * @return
 */
private RpTradePaymentOrder sealRpTradePaymentOrder(String merchantNo,String merchantName,String productName,String orderNo,Date orderDate,Date orderTime,BigDecimal orderPrice,String payWay,String payWayName,PayTypeEnum payType,String fundIntoType,String orderIp,Integer orderPeriod,String returnUrl,String notifyUrl,String remark,String field1,String field2,String field3,String field4,String field5){
  RpTradePaymentOrder rpTradePaymentOrder=new RpTradePaymentOrder();
  rpTradePaymentOrder.setProductName(productName);
  if (StringUtil.isEmpty(orderNo)) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"?????");
  }
  rpTradePaymentOrder.setMerchantOrderNo(orderNo);
  if (orderPrice == null || orderPrice.doubleValue() <= 0) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"??????");
  }
  rpTradePaymentOrder.setOrderAmount(orderPrice);
  if (StringUtil.isEmpty(merchantName)) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"??????");
  }
  rpTradePaymentOrder.setMerchantName(merchantName);
  if (StringUtil.isEmpty(merchantNo)) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"??????");
  }
  rpTradePaymentOrder.setMerchantNo(merchantNo);
  if (orderDate == null) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"??????");
  }
  rpTradePaymentOrder.setOrderDate(orderDate);
  if (orderTime == null) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"??????");
  }
  rpTradePaymentOrder.setOrderTime(orderTime);
  rpTradePaymentOrder.setOrderIp(orderIp);
  rpTradePaymentOrder.setOrderRefererUrl("");
  if (StringUtil.isEmpty(returnUrl)) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"????????");
  }
  rpTradePaymentOrder.setReturnUrl(returnUrl);
  if (StringUtil.isEmpty(notifyUrl)) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"????????");
  }
  rpTradePaymentOrder.setNotifyUrl(notifyUrl);
  if (orderPeriod == null || orderPeriod <= 0) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"???????");
  }
  rpTradePaymentOrder.setOrderPeriod(orderPeriod);
  Date expireTime=DateUtils.addMinute(orderTime,orderPeriod);
  rpTradePaymentOrder.setExpireTime(expireTime);
  rpTradePaymentOrder.setPayWayCode(payWay);
  rpTradePaymentOrder.setPayWayName(payWayName);
  rpTradePaymentOrder.setStatus(TradeStatusEnum.WAITING_PAYMENT.name());
  if (payType != null) {
    rpTradePaymentOrder.setPayTypeCode(payType.name());
    rpTradePaymentOrder.setPayTypeName(payType.getDesc());
  }
  rpTradePaymentOrder.setFundIntoType(fundIntoType);
  rpTradePaymentOrder.setRemark(remark);
  rpTradePaymentOrder.setField1(field1);
  rpTradePaymentOrder.setField2(field2);
  rpTradePaymentOrder.setField3(field3);
  rpTradePaymentOrder.setField4(field4);
  rpTradePaymentOrder.setField5(field5);
  return rpTradePaymentOrder;
}
