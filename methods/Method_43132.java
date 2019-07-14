public BitfinexOrderStatusResponse placeBitfinexMarketOrder(MarketOrder marketOrder,BitfinexOrderType bitfinexOrderType) throws IOException {
  String pair=BitfinexUtils.toPairString(marketOrder.getCurrencyPair());
  String type=(marketOrder.getType().equals(OrderType.BID) || marketOrder.getType().equals(OrderType.EXIT_ASK)) ? "buy" : "sell";
  String orderType=bitfinexOrderType.toString();
  BitfinexOrderStatusResponse newOrder=bitfinex.newOrder(apiKey,payloadCreator,signatureCreator,new BitfinexNewOrderRequest(String.valueOf(exchange.getNonceFactory().createValue()),pair,marketOrder.getOriginalAmount(),BigDecimal.ONE,"bitfinex",type,orderType,null));
  return newOrder;
}
