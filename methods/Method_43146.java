@Override public void remoteInit() throws IOException, ExchangeException {
  org.knowm.xchange.bitfinex.v1.service.BitfinexMarketDataServiceRaw dataService=new org.knowm.xchange.bitfinex.v1.service.BitfinexMarketDataServiceRaw(this);
  List<CurrencyPair> currencyPairs=dataService.getExchangeSymbols();
  exchangeMetaData=BitfinexAdapters.adaptMetaData(currencyPairs,exchangeMetaData);
  org.knowm.xchange.bitfinex.v2.service.BitfinexMarketDataServiceRaw dataServiceV2=new org.knowm.xchange.bitfinex.v2.service.BitfinexMarketDataServiceRaw(this);
  Map<CurrencyPair,BigDecimal> lastPrices=Arrays.stream(dataServiceV2.getBitfinexTickers(currencyPairs)).map(org.knowm.xchange.bitfinex.v2.BitfinexAdapters::adaptTicker).collect(Collectors.toMap(t -> t.getCurrencyPair(),t -> t.getLast()));
  final List<BitfinexSymbolDetail> symbolDetails=dataService.getSymbolDetails();
  exchangeMetaData=BitfinexAdapters.adaptMetaData(exchangeMetaData,symbolDetails,lastPrices);
  if (exchangeSpecification.getApiKey() != null && exchangeSpecification.getSecretKey() != null) {
    org.knowm.xchange.bitfinex.v1.service.BitfinexAccountService accountService=new org.knowm.xchange.bitfinex.v1.service.BitfinexAccountService(this);
    final BitfinexAccountFeesResponse accountFees=accountService.getAccountFees();
    exchangeMetaData=BitfinexAdapters.adaptMetaData(accountFees,exchangeMetaData);
    org.knowm.xchange.bitfinex.v1.service.BitfinexTradeService tradeService=new org.knowm.xchange.bitfinex.v1.service.BitfinexTradeService(this);
    final BitfinexAccountInfosResponse[] bitfinexAccountInfos=tradeService.getBitfinexAccountInfos();
    exchangeMetaData=BitfinexAdapters.adaptMetaData(bitfinexAccountInfos,exchangeMetaData);
  }
}
