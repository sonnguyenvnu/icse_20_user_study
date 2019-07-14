/** 
 * ????,?????????????????????
 * @param payKey      ????key
 * @param authCode    ?????
 * @param productName ????
 * @param orderNo     ?????
 * @param orderDate   ????
 * @param orderTime   ????
 * @param orderPrice  ????(?)
 * @param payWayCode  ????
 * @param orderIp     ??IP
 * @param remark      ????
 * @param field1      ????1
 * @param field2      ????2
 * @param field3      ????3
 * @param field4      ????4
 * @param field5      ????5
 * @return
 */
@Override public F2FPayResultVo f2fPay(String payKey,String authCode,String productName,String orderNo,Date orderDate,Date orderTime,BigDecimal orderPrice,String payWayCode,String orderIp,String remark,String field1,String field2,String field3,String field4,String field5){
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByPayKey(payKey);
  if (rpUserPayConfig == null) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  if (StringUtil.isEmpty(authCode)) {
    throw new TradeBizException(TradeBizException.TRADE_PARAM_ERROR,"?????????");
  }
  RpPayWay payWay=null;
  PayTypeEnum payType=null;
  if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
    payWay=rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(),payWayCode,PayTypeEnum.MICRO_PAY.name());
    payType=PayTypeEnum.MICRO_PAY;
  }
 else   if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
    payWay=rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(),payWayCode,PayTypeEnum.F2F_PAY.name());
    payType=PayTypeEnum.F2F_PAY;
  }
  if (payWay == null) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  String merchantNo=rpUserPayConfig.getUserNo();
  RpUserInfo rpUserInfo=rpUserInfoService.getDataByMerchentNo(merchantNo);
  if (rpUserInfo == null) {
    throw new UserBizException(UserBizException.USER_IS_NULL,"?????");
  }
  RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo,orderNo);
  if (rpTradePaymentOrder == null) {
    rpTradePaymentOrder=sealRpTradePaymentOrder(merchantNo,rpUserInfo.getUserName(),productName,orderNo,orderDate,orderTime,orderPrice,payWayCode,PayWayEnum.getEnum(payWayCode).getDesc(),payType,rpUserPayConfig.getFundIntoType(),orderIp,5,"f2fPay","f2fPay",remark,field1,field2,field3,field4,field5);
    rpTradePaymentOrderDao.insert(rpTradePaymentOrder);
  }
 else {
    if (rpTradePaymentOrder.getOrderAmount().compareTo(orderPrice) != 0) {
      throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"?????");
    }
    if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
      throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"???????,??????");
    }
  }
  return getF2FPayResultVo(rpTradePaymentOrder,payWay,payKey,rpUserPayConfig.getPaySecret(),authCode,null);
}
