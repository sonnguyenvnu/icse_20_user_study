public QuoineOrderResponse placeLimitOrder(CurrencyPair currencyPair,String type,BigDecimal originalAmount,BigDecimal price) throws IOException {
  int productId=productId(currencyPair);
  QuoineNewOrderRequest quoineNewOrderRequest=useMargin ? new QuoineNewMarginOrderRequest("limit",productId,type,originalAmount,price,leverageLevel,currencyPair.counter.getCurrencyCode()) : new QuoineNewOrderRequest("limit",productId,type,originalAmount,price);
  try {
    return quoine.placeOrder(QUOINE_API_VERSION,signatureCreator,contentType,new QuoineNewOrderRequestWrapper(quoineNewOrderRequest));
  }
 catch (  HttpStatusIOException e) {
    throw handleHttpError(e);
  }
}
