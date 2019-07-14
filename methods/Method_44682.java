public List<OkCoinFutureKline> getFutureKline(CurrencyPair currencyPair,OkCoinKlineType type,FuturesContract contractType) throws IOException {
  return getFutureKline(currencyPair,type,contractType,0,0L);
}
