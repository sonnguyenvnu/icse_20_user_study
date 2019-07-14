private static List<LimitOrder> adaptLimitOrders(LykkeOrderBook lykkeOrderBook,Order.OrderType orderType,CurrencyPair currencyPair) throws IOException {
  List<LimitOrder> limitOrders=new ArrayList<>();
  for (  LykkePrices lykkePrices : lykkeOrderBook.getPrices()) {
    limitOrders.add(new LimitOrder(orderType,new BigDecimal(Math.abs(lykkePrices.getVolume())).setScale(8,RoundingMode.HALF_EVEN).stripTrailingZeros(),currencyPair,null,DateUtils.fromISO8601DateString(lykkeOrderBook.getTimestamp()),new BigDecimal(lykkePrices.getPrice()).setScale(8,RoundingMode.HALF_EVEN).stripTrailingZeros()));
  }
  return limitOrders;
}
