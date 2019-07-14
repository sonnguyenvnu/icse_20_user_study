/** 
 * ??????KEY ?????? ??????
 * @param payKey ????KEY
 * @param orderNo ?????
 * @return
 */
@Override public OrderPayResultVo getPayResult(String payKey,String orderNo){
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByPayKey(payKey);
  if (rpUserPayConfig == null) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  String merchantNo=rpUserPayConfig.getUserNo();
  RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo,orderNo);
  RpTradePaymentRecord rpTradePaymentRecord=rpTradePaymentRecordDao.getSuccessRecordByMerchantNoAndMerchantOrderNo(rpTradePaymentOrder.getMerchantNo(),rpTradePaymentOrder.getMerchantOrderNo());
  OrderPayResultVo orderPayResultVo=new OrderPayResultVo();
  if (rpTradePaymentOrder != null && TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
    orderPayResultVo.setStatus(PublicEnum.YES.name());
    orderPayResultVo.setOrderPrice(rpTradePaymentOrder.getOrderAmount());
    orderPayResultVo.setProductName(rpTradePaymentOrder.getProductName());
    String url=getMerchantNotifyUrl(rpTradePaymentRecord,rpTradePaymentOrder,rpTradePaymentRecord.getReturnUrl(),TradeStatusEnum.SUCCESS);
    orderPayResultVo.setReturnUrl(url);
  }
  return orderPayResultVo;
}
