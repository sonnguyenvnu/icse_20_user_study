public ANXGenericResponse placeANXLimitOrder(CurrencyPair currencyPair,String type,BigDecimal amount,BigDecimal price) throws IOException {
  try {
    ANXGenericResponse anxGenericResponse=anxV2.placeOrder(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),currencyPair.base.getCurrencyCode(),currencyPair.counter.getCurrencyCode(),type,amount,price);
    return anxGenericResponse;
  }
 catch (  ANXException e) {
    throw handleError(e);
  }
catch (  HttpStatusIOException e) {
    throw handleHttpError(e);
  }
}
