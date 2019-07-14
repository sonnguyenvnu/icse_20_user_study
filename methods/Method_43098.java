@Override public void remoteInit() throws IOException, ExchangeException {
  try {
    BitfinexMarketDataServiceRaw dataService=(BitfinexMarketDataServiceRaw)this.marketDataService;
    List<CurrencyPair> currencyPairs=dataService.getExchangeSymbols();
    exchangeMetaData=BitfinexAdapters.adaptMetaData(currencyPairs,exchangeMetaData);
    org.knowm.xchange.bitfinex.v2.service.BitfinexMarketDataServiceRaw dataServiceV2=new org.knowm.xchange.bitfinex.v2.service.BitfinexMarketDataServiceRaw(this);
    Map<CurrencyPair,BigDecimal> lastPrices=Arrays.stream(dataServiceV2.getBitfinexTickers(currencyPairs)).map(org.knowm.xchange.bitfinex.v2.BitfinexAdapters::adaptTicker).collect(Collectors.toMap(t -> t.getCurrencyPair(),t -> t.getLast()));
    final List<BitfinexSymbolDetail> symbolDetails=dataService.getSymbolDetails();
    exchangeMetaData=BitfinexAdapters.adaptMetaData(exchangeMetaData,symbolDetails,lastPrices);
    if (exchangeSpecification.getApiKey() != null && exchangeSpecification.getSecretKey() != null) {
      BitfinexAccountService accountService=(BitfinexAccountService)this.accountService;
      final BitfinexAccountFeesResponse accountFees=accountService.getAccountFees();
      exchangeMetaData=BitfinexAdapters.adaptMetaData(accountFees,exchangeMetaData);
      BitfinexTradeService tradeService=(BitfinexTradeService)this.tradeService;
      final BitfinexAccountInfosResponse[] bitfinexAccountInfos=tradeService.getBitfinexAccountInfos();
      exchangeMetaData=BitfinexAdapters.adaptMetaData(bitfinexAccountInfos,exchangeMetaData);
    }
  }
 catch (  BitfinexException e) {
    throw BitfinexErrorAdapter.adapt(e);
  }
}
