private static BigDecimal getAsk(OrderBook orderBook){
  return orderBook.getAsks().stream().map(LimitOrder::getLimitPrice).sorted(Comparator.naturalOrder()).findFirst().orElse(null);
}
