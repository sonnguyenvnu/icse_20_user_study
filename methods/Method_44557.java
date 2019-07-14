private static List<LimitOrder> toLimitOrderList(List<LivecoinOrder> levels,OrderType orderType,CurrencyPair currencyPair){
  if (levels == null || levels.isEmpty()) {
    return Collections.emptyList();
  }
  List<LimitOrder> allLevels=new ArrayList<>(levels.size());
  for (  LivecoinOrder ask : levels) {
    if (ask != null) {
      allLevels.add(new LimitOrder(orderType,ask.getQuantity(),currencyPair,"0",null,ask.getRate()));
    }
  }
  return allLevels;
}
