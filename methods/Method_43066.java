static List<LimitOrder> adaptBitcointoyouPublicOrders(List<List<BigDecimal>> list,OrderType orderType,CurrencyPair currencyPair){
  List<BitcointoyouLevel> levels=new ArrayList<>();
  for (  List<BigDecimal> rawLevel : list) {
    levels.add(adaptRawBitcointoyouLevel(rawLevel));
  }
  List<LimitOrder> orders=new ArrayList<>();
  for (  BitcointoyouLevel level : levels) {
    LimitOrder limitOrder=new LimitOrder.Builder(orderType,currencyPair).originalAmount(level.getAmount()).limitPrice(level.getLimit()).build();
    orders.add(limitOrder);
  }
  return orders;
}
