private static BigDecimal getBid(OrderBook orderBook){
  return orderBook.getBids().stream().map(LimitOrder::getLimitPrice).sorted(Comparator.reverseOrder()).findFirst().orElse(null);
}
