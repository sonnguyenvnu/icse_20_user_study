public static List<CurrencyPair> getPairs(OpenOrdersParams params,Exchange exchange){
  List<CurrencyPair> pairs=new ArrayList<>();
  if (params instanceof OpenOrdersParamCurrencyPair) {
    final CurrencyPair paramsCp=((OpenOrdersParamCurrencyPair)params).getCurrencyPair();
    if (paramsCp != null) {
      pairs.add(paramsCp);
    }
  }
  if (pairs.isEmpty()) {
    pairs=exchange.getExchangeSymbols();
  }
  return pairs;
}
