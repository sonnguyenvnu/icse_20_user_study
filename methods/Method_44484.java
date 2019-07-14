/** 
 * Imperfect implementation. Kucoin appears to enforce a base <strong>and</strong> quote min <strong>and max</strong> amount that the XChange API current doesn't take account of.
 * @param exchangeMetaData The static exchange metadata.
 * @param kucoinSymbols Kucoin symbol data.
 * @return Exchange metadata.
 */
public static ExchangeMetaData adaptMetadata(ExchangeMetaData exchangeMetaData,List<SymbolResponse> kucoinSymbols){
  Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=exchangeMetaData.getCurrencyPairs();
  Map<Currency,CurrencyMetaData> currencies=exchangeMetaData.getCurrencies();
  for (  SymbolResponse symbol : kucoinSymbols) {
    CurrencyPair pair=adaptCurrencyPair(symbol.getSymbol());
    CurrencyPairMetaData staticMetaData=exchangeMetaData.getCurrencyPairs().get(pair);
    BigDecimal minSize=symbol.getBaseMinSize();
    BigDecimal maxSize=symbol.getBaseMaxSize();
    int priceScale=symbol.getQuoteIncrement().stripTrailingZeros().scale();
    CurrencyPairMetaData cpmd=new CurrencyPairMetaData(null,minSize,maxSize,priceScale,staticMetaData != null ? staticMetaData.getFeeTiers() : null);
    currencyPairs.put(pair,cpmd);
    if (!currencies.containsKey(pair.base))     currencies.put(pair.base,null);
    if (!currencies.containsKey(pair.counter))     currencies.put(pair.counter,null);
  }
  return new ExchangeMetaData(currencyPairs,currencies,exchangeMetaData.getPublicRateLimits(),exchangeMetaData.getPrivateRateLimits(),true);
}
