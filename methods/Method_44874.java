@Override public Collection<Order> getOrder(OrderQueryParams... orderQueryParams) throws IOException {
  return Arrays.stream(orderQueryParams).map(p -> {
    if (!(p instanceof OrderQueryParamCurrencyPair))     throw new NotYetImplementedForExchangeException();
    MatchingEngine engine=exchange.getEngine(((OrderQueryParamCurrencyPair)p).getCurrencyPair());
    Optional<LimitOrder> first=engine.openOrders(getApiKey()).stream().filter(o -> o.getId().equals(p.getOrderId())).findFirst();
    return first.orElse(null);
  }
).filter(Objects::nonNull).collect(Collectors.toList());
}
