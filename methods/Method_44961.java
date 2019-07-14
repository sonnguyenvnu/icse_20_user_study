private static List<LimitOrder> toLimitOrderList(ZaifFullBookTier[] levels,Order.OrderType orderType,CurrencyPair currencyPair){
  List<LimitOrder> allLevels=new ArrayList<>();
  if (levels != null) {
    for (    ZaifFullBookTier tier : levels) {
      allLevels.add(new LimitOrder(orderType,tier.getVolume(),currencyPair,"",null,tier.getPrice()));
    }
  }
  return allLevels;
}
