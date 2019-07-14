@Override public void remoteInit() throws IOException, ExchangeException {
  GeminiMarketDataServiceRaw dataService=(GeminiMarketDataServiceRaw)this.marketDataService;
  List<CurrencyPair> currencyPairs=dataService.getExchangeSymbols();
  exchangeMetaData=GeminiAdapters.adaptMetaData(currencyPairs,exchangeMetaData);
}
