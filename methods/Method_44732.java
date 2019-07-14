public static List<LimitOrder> adaptPoloniexPublicOrders(List<List<BigDecimal>> rawLevels,OrderType orderType,CurrencyPair currencyPair){
  List<PoloniexLevel> levels=new ArrayList<>();
  for (  List<BigDecimal> rawLevel : rawLevels) {
    levels.add(adaptRawPoloniexLevel(rawLevel));
  }
  List<LimitOrder> orders=new ArrayList<>();
  for (  PoloniexLevel level : levels) {
    LimitOrder limitOrder=new LimitOrder.Builder(orderType,currencyPair).originalAmount(level.getAmount()).limitPrice(level.getLimit()).build();
    orders.add(limitOrder);
  }
  return orders;
}
