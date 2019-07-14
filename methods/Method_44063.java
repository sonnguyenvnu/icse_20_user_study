private void collectMetaData(DeribitInstrument instrument,List<CurrencyPair> activeCurrencyPairs,Set<Currency> activeCurrencies){
  instrument.getBaseCurrency();
  String baseSymbol=instrument.getBaseCurrency();
  String counterSymbol=extractContractName(instrument);
  activeCurrencies.add(new Currency(baseSymbol));
  activeCurrencies.add(new Currency(counterSymbol));
  activeCurrencyPairs.add(new CurrencyPair(baseSymbol,counterSymbol));
}
