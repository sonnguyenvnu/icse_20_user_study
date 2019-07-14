public UpbitOrderResponse limitOrder(LimitOrder limitOrder) throws IOException {
  UpbitOrderRequest upbitOrderRequest=new UpbitOrderRequest();
  String marketId=limitOrder.getCurrencyPair().counter + "-" + limitOrder.getCurrencyPair().base;
  upbitOrderRequest.setMarketId(marketId);
  upbitOrderRequest.setOrderType(limitOrder.getType().name().toLowerCase());
  upbitOrderRequest.setVolume(limitOrder.getOriginalAmount().toString());
  upbitOrderRequest.setPrice(limitOrder.getLimitPrice().toString());
  upbitOrderRequest.setSide(limitOrder.getType().equals(Order.OrderType.ASK) ? "ask" : "bid");
  upbitOrderRequest.setOrderType("limit");
  return upbit.limitOrder(this.signatureCreator,upbitOrderRequest);
}
