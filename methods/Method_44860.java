public synchronized List<LimitOrder> openOrders(String apiKey){
  return Stream.concat(asks.stream(),bids.stream()).flatMap(v -> v.getOrders().stream()).filter(o -> o.getApiKey().equals(apiKey)).sorted(Ordering.natural().onResultOf(BookOrder::getTimestamp).reversed()).map(o -> o.toOrder(currencyPair)).collect(toList());
}
