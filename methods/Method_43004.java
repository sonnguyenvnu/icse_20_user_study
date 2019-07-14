public BinancePrice tickerPrice(CurrencyPair pair) throws IOException {
  return tickerAllPrices().stream().filter(p -> p.getCurrencyPair().equals(pair)).collect(StreamUtils.singletonCollector());
}
