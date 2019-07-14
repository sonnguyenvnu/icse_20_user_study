/** 
 * ?????,??????????????? ?????,??????????,???????URL
 * @param payWayCode
 * @param resultMap
 * @return
 */
@Override public OrderPayResultVo completeScanPayByResult(String payWayCode,Map<String,String> resultMap){
  OrderPayResultVo orderPayResultVo=new OrderPayResultVo();
  String bankOrderNo=resultMap.get("out_trade_no");
  RpTradePaymentRecord rpTradePaymentRecord=rpTradePaymentRecordDao.getByBankOrderNo(bankOrderNo);
  if (rpTradePaymentRecord == null) {
    throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,",????,?????");
  }
  orderPayResultVo.setOrderPrice(rpTradePaymentRecord.getOrderAmount());
  orderPayResultVo.setProductName(rpTradePaymentRecord.getProductName());
  RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(rpTradePaymentRecord.getMerchantNo(),rpTradePaymentRecord.getMerchantOrderNo());
  String trade_status=resultMap.get("trade_status");
  boolean verify_result=AlipayNotify.verify(resultMap);
  if (verify_result) {
    if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
      String resultUrl=getMerchantNotifyUrl(rpTradePaymentRecord,rpTradePaymentOrder,rpTradePaymentRecord.getReturnUrl(),TradeStatusEnum.SUCCESS);
      orderPayResultVo.setReturnUrl(resultUrl);
      orderPayResultVo.setStatus(TradeStatusEnum.SUCCESS.name());
    }
 else {
      String resultUrl=getMerchantNotifyUrl(rpTradePaymentRecord,rpTradePaymentOrder,rpTradePaymentRecord.getReturnUrl(),TradeStatusEnum.FAILED);
      orderPayResultVo.setReturnUrl(resultUrl);
      orderPayResultVo.setStatus(TradeStatusEnum.FAILED.name());
    }
  }
 else {
    throw new TradeBizException(TradeBizException.TRADE_ALIPAY_ERROR,"???????");
  }
  return orderPayResultVo;
}
