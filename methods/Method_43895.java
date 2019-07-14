@Override public List<CurrencyPair> getExchangeSymbols(){
  return new ArrayList<>(getExchangeMetaData().getCurrencyPairs().keySet());
}
