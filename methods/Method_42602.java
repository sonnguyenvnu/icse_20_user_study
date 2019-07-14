/** 
 * ???????????,????????????? 1:??(???? + ?????)???????? 1.1:??????,???,??????? 1.2:???????,?????? 2:?????? 3:???????? 4:?????????????
 * @param payKey      ????KEY
 * @param productName ????
 * @param orderNo     ?????
 * @param orderDate   ????
 * @param orderTime   ????
 * @param orderPrice  ????(?)
 * @param payWayCode  ??????
 * @param orderIp     ??IP
 * @param orderPeriod ?????(??)
 * @param returnUrl   ??????????
 * @param notifyUrl   ??????????
 * @param remark      ????
 * @param field1      ????1
 * @param field2      ????2
 * @param field3      ????3
 * @param field4      ????4
 * @param field5      ????5
 */
@Override @Transactional(rollbackFor=Exception.class) public ScanPayResultVo initDirectScanPay(String payKey,String productName,String orderNo,Date orderDate,Date orderTime,BigDecimal orderPrice,String payWayCode,String orderIp,Integer orderPeriod,String returnUrl,String notifyUrl,String remark,String field1,String field2,String field3,String field4,String field5){
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByPayKey(payKey);
  if (rpUserPayConfig == null) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  RpPayWay payWay=null;
  PayTypeEnum payType=null;
  if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
    payWay=rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(),payWayCode,PayTypeEnum.SCANPAY.name());
    payType=PayTypeEnum.SCANPAY;
  }
 else   if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
    payWay=rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(),payWayCode,PayTypeEnum.DIRECT_PAY.name());
    payType=PayTypeEnum.DIRECT_PAY;
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
    rpTradePaymentOrder=sealRpTradePaymentOrder(merchantNo,rpUserInfo.getUserName(),productName,orderNo,orderDate,orderTime,orderPrice,payWayCode,PayWayEnum.getEnum(payWayCode).getDesc(),payType,rpUserPayConfig.getFundIntoType(),orderIp,orderPeriod,returnUrl,notifyUrl,remark,field1,field2,field3,field4,field5);
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
  return getScanPayResultVo(rpTradePaymentOrder,payWay);
}
