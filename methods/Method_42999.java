public List<BinanceAggTrades> aggTrades(CurrencyPair pair,Long fromId,Long startTime,Long endTime,Integer limit) throws IOException {
  return binance.aggTrades(BinanceAdapters.toSymbol(pair),fromId,startTime,endTime,limit);
}
