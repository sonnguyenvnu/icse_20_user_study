public ANXDepthWrapper getANXFullOrderBook(CurrencyPair currencyPair) throws IOException {
  try {
    ANXDepthWrapper anxDepthWrapper=anxV2.getFullDepth(currencyPair.base.getCurrencyCode(),currencyPair.counter.getCurrencyCode());
    return anxDepthWrapper;
  }
 catch (  ANXException e) {
    throw handleError(e);
  }
catch (  HttpStatusIOException e) {
    throw handleHttpError(e);
  }
}
