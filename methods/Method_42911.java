public ANXDepthWrapper getANXPartialOrderBook(CurrencyPair currencyPair) throws IOException {
  try {
    ANXDepthWrapper anxDepthWrapper=anxV2.getPartialDepth(currencyPair.base.getCurrencyCode(),currencyPair.counter.getCurrencyCode());
    return anxDepthWrapper;
  }
 catch (  ANXException e) {
    throw handleError(e);
  }
catch (  HttpStatusIOException e) {
    throw handleHttpError(e);
  }
}
