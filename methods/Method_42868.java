public List<LimitOrder> mapOrders(CurrencyPair currencyPair,List<AcxOrder> orders){
  return orders.stream().map(o -> mapOrder(currencyPair,o)).collect(Collectors.toList());
}
