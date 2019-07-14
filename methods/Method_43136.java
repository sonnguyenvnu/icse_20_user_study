public BitfinexNewOrderMultiResponse placeBitfinexOrderMulti(List<? extends Order> orders,BitfinexOrderType bitfinexOrderType) throws IOException {
  BitfinexNewOrder[] bitfinexOrders=new BitfinexNewOrder[orders.size()];
  for (int i=0; i < bitfinexOrders.length; i++) {
    Order o=orders.get(i);
    if (o instanceof LimitOrder) {
      LimitOrder limitOrder=(LimitOrder)o;
      String pair=BitfinexUtils.toPairString(limitOrder.getCurrencyPair());
      String type=(limitOrder.getType().equals(OrderType.BID) || limitOrder.getType().equals(OrderType.EXIT_ASK)) ? "buy" : "sell";
      String orderType=bitfinexOrderType.toString();
      bitfinexOrders[i]=new BitfinexNewOrder(pair,"bitfinex",type,orderType,limitOrder.getOriginalAmount(),limitOrder.getLimitPrice());
    }
 else     if (o instanceof MarketOrder) {
      MarketOrder marketOrder=(MarketOrder)o;
      String pair=BitfinexUtils.toPairString(marketOrder.getCurrencyPair());
      String type=(marketOrder.getType().equals(OrderType.BID) || marketOrder.getType().equals(OrderType.EXIT_ASK)) ? "buy" : "sell";
      String orderType=bitfinexOrderType.toString();
      bitfinexOrders[i]=new BitfinexNewOrder(pair,"bitfinex",type,orderType,marketOrder.getOriginalAmount(),BigDecimal.ONE);
    }
  }
  BitfinexNewOrderMultiRequest request=new BitfinexNewOrderMultiRequest(String.valueOf(exchange.getNonceFactory().createValue()),bitfinexOrders);
  BitfinexNewOrderMultiResponse response=bitfinex.newOrderMulti(apiKey,payloadCreator,signatureCreator,request);
  return response;
}
