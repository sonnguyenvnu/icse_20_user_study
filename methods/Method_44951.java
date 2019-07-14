private static List<LimitOrder> toLimitOrderList(List<YoBitAsksBidsData> levels,OrderType orderType,CurrencyPair currencyPair){
  List<LimitOrder> allLevels=new ArrayList<>(levels.size());
  for (  YoBitAsksBidsData ask : levels) {
    if (ask != null) {
      allLevels.add(new LimitOrder(orderType,ask.getQuantity(),currencyPair,"0",null,ask.getRate()));
    }
  }
  return allLevels;
}
