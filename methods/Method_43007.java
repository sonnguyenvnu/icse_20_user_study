public void placeTestOrder(OrderType type,Order order,BigDecimal limitPrice,BigDecimal stopPrice) throws IOException {
  try {
    Long recvWindow=(Long)exchange.getExchangeSpecification().getExchangeSpecificParametersItem("recvWindow");
    testNewOrder(order.getCurrencyPair(),BinanceAdapters.convert(order.getType()),type,TimeInForce.GTC,order.getOriginalAmount(),limitPrice,getClientOrderId(order),stopPrice,null,recvWindow,getTimestamp());
  }
 catch (  BinanceException e) {
    throw BinanceErrorAdapter.adapt(e);
  }
}
