@Override public void remoteInit() throws IOException {
  Map<CurrencyPair,GateioMarketInfo> currencyPair2BTERMarketInfoMap=((GateioMarketDataServiceRaw)marketDataService).getBTERMarketInfo();
  exchangeMetaData=GateioAdapters.adaptToExchangeMetaData(currencyPair2BTERMarketInfoMap);
}
