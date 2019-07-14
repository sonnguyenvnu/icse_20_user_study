public BinanceKline lastKline(CurrencyPair pair,KlineInterval interval) throws IOException {
  return klines(pair,interval,1,null,null).stream().collect(StreamUtils.singletonCollector());
}
