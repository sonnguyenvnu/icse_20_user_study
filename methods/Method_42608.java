/** 
 * ????????????,?????????????? 1:??(???? + ?????)???????? 1.1:???????????,?????????? 1.2:???????,?????? 2:????????,???????,??????
 * @param payKey      ????KEY
 * @param productName ????
 * @param orderNo     ?????
 * @param orderDate   ????
 * @param orderTime   ????
 * @param orderPrice  ????(?)
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
@Override @Transactional(rollbackFor=Exception.class) public RpPayGateWayPageShowVo initNonDirectScanPay(String payKey,String productName,String orderNo,Date orderDate,Date orderTime,BigDecimal orderPrice,String orderIp,Integer orderPeriod,String returnUrl,String notifyUrl,String remark,String field1,String field2,String field3,String field4,String field5){
  RpUserPayConfig rpUserPayConfig=rpUserPayConfigService.getByPayKey(payKey);
  if (rpUserPayConfig == null) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  String merchantNo=rpUserPayConfig.getUserNo();
  RpUserInfo rpUserInfo=rpUserInfoService.getDataByMerchentNo(merchantNo);
  if (rpUserInfo == null) {
    throw new UserBizException(UserBizException.USER_IS_NULL,"?????");
  }
  List<RpPayWay> payWayList=rpPayWayService.listByProductCode(rpUserPayConfig.getProductCode());
  if (payWayList == null || payWayList.size() <= 0) {
    throw new UserBizException(UserBizException.USER_PAY_CONFIG_ERRPR,"????????");
  }
  RpTradePaymentOrder rpTradePaymentOrder=rpTradePaymentOrderDao.selectByMerchantNoAndMerchantOrderNo(merchantNo,orderNo);
  if (rpTradePaymentOrder == null) {
    rpTradePaymentOrder=sealRpTradePaymentOrder(merchantNo,rpUserInfo.getUserName(),productName,orderNo,orderDate,orderTime,orderPrice,null,null,null,rpUserPayConfig.getFundIntoType(),orderIp,orderPeriod,returnUrl,notifyUrl,remark,field1,field2,field3,field4,field5);
    rpTradePaymentOrderDao.insert(rpTradePaymentOrder);
  }
 else {
    if (TradeStatusEnum.SUCCESS.name().equals(rpTradePaymentOrder.getStatus())) {
      throw new TradeBizException(TradeBizException.TRADE_ORDER_ERROR,"???????,??????");
    }
    if (rpTradePaymentOrder.getOrderAmount().compareTo(orderPrice) != 0) {
      rpTradePaymentOrder.setOrderAmount(orderPrice);
      rpTradePaymentOrderDao.update(rpTradePaymentOrder);
    }
  }
  RpPayGateWayPageShowVo payGateWayPageShowVo=new RpPayGateWayPageShowVo();
  payGateWayPageShowVo.setProductName(rpTradePaymentOrder.getProductName());
  payGateWayPageShowVo.setMerchantName(rpTradePaymentOrder.getMerchantName());
  payGateWayPageShowVo.setOrderAmount(rpTradePaymentOrder.getOrderAmount());
  payGateWayPageShowVo.setMerchantOrderNo(rpTradePaymentOrder.getMerchantOrderNo());
  payGateWayPageShowVo.setPayKey(payKey);
  Map<String,PayWayEnum> payWayEnumMap=new HashMap<String,PayWayEnum>();
  for (  RpPayWay payWay : payWayList) {
    payWayEnumMap.put(payWay.getPayWayCode(),PayWayEnum.getEnum(payWay.getPayWayCode()));
  }
  payGateWayPageShowVo.setPayWayEnumMap(payWayEnumMap);
  return payGateWayPageShowVo;
}
