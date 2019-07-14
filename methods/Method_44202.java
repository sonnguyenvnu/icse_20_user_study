public List<LimitOrder> openOrders(){
  Map<String,List<Map<String,String>>> map=exmo.userOpenOrders(signatureCreator,apiKey,exchange.getNonceFactory());
  List<LimitOrder> openOrders=new ArrayList<>();
  for (  String market : map.keySet()) {
    CurrencyPair currencyPair=adaptMarket(market);
    for (    Map<String,String> order : map.get(market)) {
      Order.OrderType type=ExmoAdapters.adaptOrderType(order);
      BigDecimal amount=new BigDecimal(order.get("quantity"));
      String id=order.get("order_id");
      BigDecimal price=new BigDecimal(order.get("price"));
      Date created=DateUtils.fromUnixTime(Long.valueOf(order.get("created")));
      openOrders.add(new LimitOrder(type,amount,currencyPair,id,created,price));
    }
  }
  return openOrders;
}
