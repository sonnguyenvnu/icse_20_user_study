private static List<LimitOrder> toLimitOrderList(CoinbaseProProductBookEntry[] levels,OrderType orderType,CurrencyPair currencyPair){
  List<LimitOrder> allLevels=new ArrayList<>();
  if (levels != null) {
    for (int i=0; i < levels.length; i++) {
      CoinbaseProProductBookEntry ask=levels[i];
      allLevels.add(new LimitOrder(orderType,ask.getVolume(),currencyPair,"0",null,ask.getPrice()));
    }
  }
  return allLevels;
}
