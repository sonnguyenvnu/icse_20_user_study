public List<BinanceKline> klines(CurrencyPair pair,KlineInterval interval) throws IOException {
  return klines(pair,interval,null,null,null);
}
