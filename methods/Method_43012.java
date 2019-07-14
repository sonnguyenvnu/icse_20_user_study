public List<BinanceTrade> myTrades(CurrencyPair pair,Integer limit,Long startTime,Long endTime,Long fromId,Long recvWindow,long timestamp) throws BinanceException, IOException {
  return binance.myTrades(BinanceAdapters.toSymbol(pair),limit,startTime,endTime,fromId,recvWindow,timestamp,super.apiKey,super.signatureCreator);
}
