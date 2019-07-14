public void updateExchangeMetaData() throws IOException {
  List<CurrencyPair> activeCurrencyPairs=new ArrayList<>();
  Set<Currency> activeCurrencies=new HashSet<>();
  List<DeribitCurrency> activeDeribitCurrencies=((DeribitMarketDataServiceRaw)marketDataService).getDeribitCurrencies();
  List<DeribitInstrument> activeDeribitInstruments=new ArrayList<>();
  for (  DeribitCurrency deribitCurrency : activeDeribitCurrencies) {
    activeDeribitInstruments.addAll(((DeribitMarketDataServiceRaw)marketDataService).getDeribitActiveInstruments(deribitCurrency.getCurrency()));
  }
  activeDeribitInstruments.forEach(activeDeribitInstrument -> collectMetaData(activeDeribitInstrument,activeCurrencyPairs,activeCurrencies));
  Map<CurrencyPair,CurrencyPairMetaData> pairsMap=exchangeMetaData.getCurrencyPairs();
  Map<Currency,CurrencyMetaData> currenciesMap=exchangeMetaData.getCurrencies();
  pairsMap.keySet().retainAll(activeCurrencyPairs);
  currenciesMap.keySet().retainAll(activeCurrencies);
  activeCurrencyPairs.forEach(cp -> {
    if (!pairsMap.containsKey(cp)) {
      pairsMap.put(cp,null);
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
