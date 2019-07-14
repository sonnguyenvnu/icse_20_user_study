public static List<LimitOrder> adaptBids(final List<LiquiPublicBid> orders,final CurrencyPair currencyPair){
  return orders.stream().map(ask -> adaptOrder(ask,currencyPair)).collect(Collectors.toList());
}
