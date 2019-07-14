@Override public void remoteInit() throws IOException, ExchangeException {
  BitcoinChartsTicker[] tickers=((BitcoinChartsMarketDataService)marketDataService).getBitcoinChartsTickers();
  exchangeMetaData=BitcoinChartsAdapters.adaptMetaData(exchangeMetaData,tickers);
}
