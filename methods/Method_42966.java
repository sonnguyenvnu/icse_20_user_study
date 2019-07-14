@Override public void remoteInit(){
  try {
    Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=exchangeMetaData.getCurrencyPairs();
    Map<Currency,CurrencyMetaData> currencies=exchangeMetaData.getCurrencies();
    BinanceMarketDataService marketDataService=(BinanceMarketDataService)this.marketDataService;
    exchangeInfo=marketDataService.getExchangeInfo();
    Symbol[] symbols=exchangeInfo.getSymbols();
    BinanceAccountService accountService=(BinanceAccountService)getAccountService();
    Map<String,AssetDetail> assetDetailMap=accountService.getAssetDetails();
    for (    Symbol symbol : symbols) {
      if (!symbol.getStatus().equals("BREAK")) {
        int basePrecision=Integer.parseInt(symbol.getBaseAssetPrecision());
        int counterPrecision=Integer.parseInt(symbol.getQuotePrecision());
        int pairPrecision=8;
        int amountPrecision=8;
        BigDecimal minQty=null;
        BigDecimal maxQty=null;
        BigDecimal stepSize=null;
        Filter[] filters=symbol.getFilters();
        CurrencyPair currentCurrencyPair=new CurrencyPair(symbol.getBaseAsset(),symbol.getQuoteAsset());
        for (        Filter filter : filters) {
          if (filter.getFilterType().equals("PRICE_FILTER")) {
            pairPrecision=Math.min(pairPrecision,numberOfDecimals(filter.getTickSize()));
          }
 else           if (filter.getFilterType().equals("LOT_SIZE")) {
            amountPrecision=Math.min(amountPrecision,numberOfDecimals(filter.getMinQty()));
            minQty=new BigDecimal(filter.getMinQty()).stripTrailingZeros();
            maxQty=new BigDecimal(filter.getMaxQty()).stripTrailingZeros();
            stepSize=new BigDecimal(filter.getStepSize()).stripTrailingZeros();
          }
        }
        currencyPairs.put(currentCurrencyPair,new CurrencyPairMetaData(new BigDecimal("0.1"),minQty,maxQty,amountPrecision,pairPrecision,null,stepSize,null));
        Currency baseCurrency=currentCurrencyPair.base;
        BigDecimal baseWithdrawalFee=getWithdrawalFee(currencies,baseCurrency,assetDetailMap);
        currencies.put(baseCurrency,new CurrencyMetaData(basePrecision,baseWithdrawalFee));
        Currency counterCurrency=currentCurrencyPair.counter;
        BigDecimal counterWithdrawalFee=getWithdrawalFee(currencies,counterCurrency,assetDetailMap);
        currencies.put(counterCurrency,new CurrencyMetaData(counterPrecision,counterWithdrawalFee));
      }
    }
  }
 catch (  Exception e) {
    throw new ExchangeException("Failed to initialize: " + e.getMessage(),e);
  }
}
