@Override public ProgramPayResultVo programPay(String payKey,String openId,String productName,String orderNo,Date orderDate,Date orderTime,BigDecimal orderPrice,String payWayCode,String orderIp,String notifyUrl,String remark,String field1,String field2,String field3,String field4,String field5){
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByPayKey(payKey);
  if (rpUserPayConfig == null) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  RpPayWay payWay;
  PayTypeEnum payType;
  if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
    payType=PayTypeEnum.WX_PROGRAM_PAY;
    payWay=rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(),payWayCode,payType.name());
  }
 else {
    throw new TradeBizException(TradeBizException.TRADE_PAY_WAY_ERROR,"?????????");
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
    rpTradePaymentOrder=sealRpTradePaymentOrder(merchantNo,rpUserInfo.getUserName(),productName,orderNo,orderDate,orderTime,orderPrice,payWayCode,PayWayEnum.getEnum(payWayCode).getDesc(),payType,rpUserPayConfig.getFundIntoType(),orderIp,10,payType.name(),notifyUrl,remark,field1,field2,field3,field4,field5);
    rpTradePaymentOrderDao.insert(rpTradePaymentOrder);
  }
 else {
    if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
      throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"???????,??????");
    }
    if (rpTradePaymentOrder.getOrderAmount().compareTo(orderPrice) != 0) {
      rpTradePaymentOrder.setOrderAmount(orderPrice);
    }
  }
  return getProgramPayResultVo(rpTradePaymentOrder,payWay,rpUserPayConfig.getPaySecret(),openId,null);
}
