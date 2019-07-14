public CoindirectTrades getCoindirectTrades(CurrencyPair pair,String history) throws IOException {
  return coindirect.getHistoricalExchangeTrades(CoindirectAdapters.toSymbol(pair),history);
}
