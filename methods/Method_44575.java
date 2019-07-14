public String makeMarketOrder(MarketOrder order) throws IOException {
  LivecoinOrderResponse response;
  if (order.getType().equals(Order.OrderType.BID)) {
    response=service.buyWithMarketOrder(apiKey,signatureCreator,order.getCurrencyPair().toString(),order.getOriginalAmount());
  }
 else {
    response=service.sellWithMarketOrder(apiKey,signatureCreator,order.getCurrencyPair().toString(),order.getOriginalAmount());
  }
  return response.getOrderId();
}
