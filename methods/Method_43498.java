public List<CurrencyPair> getExchangeSymbols() throws IOException {
  List<CurrencyPair> pairs=new ArrayList<CurrencyPair>();
  for (  BTCTurkTicker ticker : getBTCTurkTicker()) {
    pairs.add(ticker.getPair());
  }
  return pairs;
}
