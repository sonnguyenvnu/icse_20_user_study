protected CobinhoodResponse<CobinhoodOrder.Container> placeCobinhoodLimitOrder(LimitOrder order) throws IOException {
  CobinhoodPlaceOrderRequest request=new CobinhoodPlaceOrderRequest(CobinhoodAdapters.adaptCurrencyPair(order.getCurrencyPair()),CobinhoodOrderSide.fromOrderType(order.getType()).name(),CobinhoodOrderType.limit,order.getLimitPrice().toString(),order.getOriginalAmount().toString());
  return cobinhood.placeOrder(apiKey,exchange.getNonceFactory(),request);
}
