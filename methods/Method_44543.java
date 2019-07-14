public static List<LimitOrder> adaptAsks(final List<LiquiPublicAsk> orders,final CurrencyPair currencyPair){
  return orders.stream().map(ask -> adaptOrder(ask,currencyPair)).collect(Collectors.toList());
}
