public boolean checkProductExists(CurrencyPair currencyPair){
  boolean currencyPairSupported=false;
  for (  CurrencyPair cp : exchange.getExchangeSymbols()) {
    if (cp.base.getCurrencyCode().equalsIgnoreCase(currencyPair.base.getCurrencyCode()) && cp.counter.getCurrencyCode().equalsIgnoreCase(currencyPair.counter.getCurrencyCode())) {
      currencyPairSupported=true;
      break;
    }
  }
  return currencyPairSupported;
}
