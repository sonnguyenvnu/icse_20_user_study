public List<CurrencyPair> getExchangeSymbols() throws IOException {
  List<CurrencyPair> pairs=new ArrayList<>();
  pairs.add(new CurrencyPair("GLD","BTC"));
  return pairs;
}
