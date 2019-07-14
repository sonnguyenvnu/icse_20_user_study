public Map<String,ANXTicker> getANXTickers(Collection<CurrencyPair> currencyPairs) throws IOException {
  CurrencyPair pathCurrencyPair=null;
  StringBuilder extraCurrencyPairs=new StringBuilder();
  int i=1;
  for (  CurrencyPair currencyPair : currencyPairs) {
    if (i++ == currencyPairs.size()) {
      pathCurrencyPair=currencyPair;
    }
 else {
      extraCurrencyPairs.append(currencyPair.base.getCurrencyCode()).append(currencyPair.counter.getCurrencyCode()).append(",");
    }
  }
  if (pathCurrencyPair == null) {
    return null;
  }
  try {
    if (i == 2) {
      ANXTicker anxTicker=getANXTicker(pathCurrencyPair);
      Map<String,ANXTicker> ticker=new HashMap<>();
      ticker.put(pathCurrencyPair.base.getCurrencyCode() + pathCurrencyPair.counter.getCurrencyCode(),anxTicker);
      return ticker;
    }
    ANXTickersWrapper anxTickerWrapper=anxV2.getTickers(pathCurrencyPair.base.getCurrencyCode(),pathCurrencyPair.counter.getCurrencyCode(),extraCurrencyPairs.toString());
    return anxTickerWrapper.getAnxTickers();
  }
 catch (  ANXException e) {
    throw handleError(e);
  }
catch (  HttpStatusIOException e) {
    throw handleHttpError(e);
  }
}
