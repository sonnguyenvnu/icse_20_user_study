MatchingEngine create(CurrencyPair currencyPair,int priceScale,BigDecimal minimumAmount,Consumer<Fill> onFill){
  return engines.computeIfAbsent(currencyPair,pair -> new MatchingEngine(accountFactory,pair,priceScale,minimumAmount,onFill));
}
