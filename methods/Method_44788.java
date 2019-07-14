public static OpenOrders adapteOpenOrders(QuoineOrdersList quoineOrdersList){
  List<LimitOrder> openOrders=new ArrayList<>();
  for (  Model model : quoineOrdersList.getModels()) {
    if (model.getStatus().equals("live")) {
      String baseSymbol=model.getCurrencyPairCode().replace(model.getFundingCurrency(),"");
      String counterSymbol=model.getFundingCurrency();
      CurrencyPair currencyPair=new CurrencyPair(baseSymbol,counterSymbol);
      OrderType orderType=model.getSide().equals("sell") ? OrderType.ASK : OrderType.BID;
      Date timestamp=new Date(model.getCreatedAt().longValue() * 1000L);
      LimitOrder limitOrder=new LimitOrder(orderType,model.getQuantity(),model.getFilledQuantity(),currencyPair,model.getId(),timestamp,model.getPrice());
      openOrders.add(limitOrder);
    }
  }
  return new OpenOrders(openOrders);
}
