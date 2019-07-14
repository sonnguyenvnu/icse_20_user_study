@Override public boolean accept(LimitOrder order){
  return OpenOrdersParamCurrencyPair.super.accept(order) || OpenOrdersParamMultiCurrencyPair.super.accept(order);
}
