public BinanceTicker24h ticker24h(CurrencyPair pair) throws IOException {
  BinanceTicker24h ticker24h=binance.ticker24h(BinanceAdapters.toSymbol(pair));
  ticker24h.setCurrencyPair(pair);
  return ticker24h;
}
