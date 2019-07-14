public String makeLimitOrder(LimitOrder order) throws IOException {
  LivecoinOrderResponse response;
  if (order.getType().equals(Order.OrderType.BID)) {
    response=service.buyWithLimitOrder(apiKey,signatureCreator,order.getCurrencyPair().toString(),order.getLimitPrice(),order.getOriginalAmount());
  }
 else {
    response=service.sellWithLimitOrder(apiKey,signatureCreator,order.getCurrencyPair().toString(),order.getLimitPrice(),order.getOriginalAmount());
  }
  return response.getOrderId();
}
