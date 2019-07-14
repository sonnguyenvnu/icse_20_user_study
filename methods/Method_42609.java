/** 
 * ???????,???????,???
 * @param payKey
 * @param orderNo
 * @param payWayCode
 * @return
 */
@Override public ScanPayResultVo toNonDirectScanPay(String payKey,String orderNo,String payWayCode){
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByPayKey(payKey);
  if (rpUserPayConfig == null) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  RpPayWay payWay=null;
  if (PayWayEnum.WEIXIN.name().equals(payWayCode)) {
    payWay=rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(),payWayCode,PayTypeEnum.SCANPAY.name());
  }
 else   if (PayWayEnum.ALIPAY.name().equals(payWayCode)) {
    payWay=rpPayWayService.getByPayWayTypeCode(rpUserPayConfig.getProductCode(),payWayCode,PayTypeEnum.DIRECT_PAY.name());
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
    throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"?????");
  }
  if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
    throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"???????,??????");
  }
  return getScanPayResultVo(rpTradePaymentOrder,payWay);
}
