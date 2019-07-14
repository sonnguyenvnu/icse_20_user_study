@Override public void remoteInit() throws IOException {
  CoinbaseProProduct[] products=((CoinbaseProMarketDataServiceRaw)marketDataService).getCoinbaseProProducts();
  CoinbaseProCurrency[] currencies=((CoinbaseProMarketDataServiceRaw)marketDataService).getCoinbaseProCurrencies();
  exchangeMetaData=CoinbaseProAdapters.adaptToExchangeMetaData(exchangeMetaData,products,currencies);
}
