@Override public void remoteInit() throws IOException {
  HitbtcMarketDataServiceRaw dataService=((HitbtcMarketDataServiceRaw)marketDataService);
  List<HitbtcSymbol> hitbtcSymbols=dataService.getHitbtcSymbols();
  Map<Currency,CurrencyMetaData> currencies=dataService.getHitbtcCurrencies().stream().collect(Collectors.toMap(hitbtcCurrency -> new Currency(hitbtcCurrency.getId()),hitbtcCurrency -> new CurrencyMetaData(null,hitbtcCurrency.getPayoutFee())));
  Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=hitbtcSymbols.stream().collect(Collectors.toMap(hitbtcSymbol -> new CurrencyPair(new Currency(hitbtcSymbol.getBaseCurrency()),new Currency(hitbtcSymbol.getQuoteCurrency())),hitbtcSymbol -> new CurrencyPairMetaData((BigDecimal)null,hitbtcSymbol.getQuantityIncrement(),(BigDecimal)null,hitbtcSymbol.getTickSize().scale(),(FeeTier[])null)));
  exchangeMetaData=HitbtcAdapters.adaptToExchangeMetaData(hitbtcSymbols,currencies,currencyPairs);
}
