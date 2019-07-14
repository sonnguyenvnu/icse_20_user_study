public List<CurrencyPair> getExchangeSymbols() throws IOException {
  List<CurrencyPair> currencyPairs=new ArrayList<>(bter.getPairs().getPairs());
  return currencyPairs;
}
