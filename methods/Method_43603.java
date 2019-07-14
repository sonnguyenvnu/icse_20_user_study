public CexIOFullOrder getOrderFullDetail(String orderId) throws IOException {
  Map orderRaw=cexIOAuthenticated.getOrderRaw(signatureCreator,new CexioSingleOrderIdRequest(orderId));
  ObjectMapper objectMapper=new ObjectMapper();
  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
  CexIOOpenOrder order=objectMapper.convertValue(orderRaw,CexIOOpenOrder.class);
  return new CexIOFullOrder(order.user,order.type,order.symbol1,order.symbol2,order.amount,order.remains,order.price,order.time,order.lastTxTime,order.tradingFeeStrategy,order.tradingFeeTaker,order.tradingFeeMaker,order.tradingFeeUserVolumeAmount,order.lastTx,order.status,order.orderId,order.id,(String)orderRaw.get("ta:" + order.symbol2),(String)orderRaw.get("tta:" + order.symbol2),(String)orderRaw.get("fa:" + order.symbol2),(String)orderRaw.get("tfa:" + order.symbol2));
}
