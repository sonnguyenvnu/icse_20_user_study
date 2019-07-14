public void updateExchangeMetaData(){
  List<BitmexTicker> tickers=((BitmexMarketDataServiceRaw)marketDataService).getActiveTickers();
  List<CurrencyPair> activeCurrencyPairs=new ArrayList<>();
  Set<Currency> activeCurrencies=new HashSet<>();
  tickers.forEach(ticker -> collectCurrenciesAndPairs(ticker,activeCurrencyPairs,activeCurrencies));
  Map<CurrencyPair,CurrencyPairMetaData> pairsMap=exchangeMetaData.getCurrencyPairs();
  Map<Currency,CurrencyMetaData> currenciesMap=exchangeMetaData.getCurrencies();
  pairsMap.keySet().retainAll(activeCurrencyPairs);
  currenciesMap.keySet().retainAll(activeCurrencies);
  activeCurrencyPairs.forEach(cp -> {
    if (!pairsMap.containsKey(cp)) {
      pairsMap.put(cp,new CurrencyPairMetaData(null,BigDecimal.ONE,null,getPriceScale(tickers,cp),null));
    }
    if (!currenciesMap.containsKey(cp.base)) {
      currenciesMap.put(cp.base,null);
    }
    if (!currenciesMap.containsKey(cp.counter)) {
      currenciesMap.put(cp.counter,null);
    }
  }
);
}
