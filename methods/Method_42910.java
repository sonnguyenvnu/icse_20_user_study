public Map<String,ANXDepth> getANXFullOrderBooks(Collection<CurrencyPair> currencyPairs) throws IOException {
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
      ANXDepthWrapper anxDepthWrapper=getANXFullOrderBook(pathCurrencyPair);
      Map<String,ANXDepth> book=new HashMap<>();
      book.put(pathCurrencyPair.base.getCurrencyCode() + pathCurrencyPair.counter.getCurrencyCode(),anxDepthWrapper.getAnxDepth());
      return book;
    }
    ANXDepthsWrapper anxDepthWrapper=anxV2.getFullDepths(pathCurrencyPair.base.getCurrencyCode(),pathCurrencyPair.counter.getCurrencyCode(),extraCurrencyPairs.toString());
    return anxDepthWrapper.getAnxDepths();
  }
 catch (  ANXException e) {
    throw handleError(e);
  }
catch (  HttpStatusIOException e) {
    throw handleHttpError(e);
  }
}
