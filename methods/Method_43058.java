public BitcoindeIdResponse bitcoindeCancelOrders(String order_id,CurrencyPair currencyPair) throws IOException {
  try {
    String currPair=currencyPair.base.getCurrencyCode() + currencyPair.counter.getCurrencyCode();
    return bitcoinde.deleteOrder(apiKey,nonceFactory,signatureCreator,order_id,currPair);
  }
 catch (  BitcoindeException e) {
    throw handleError(e);
  }
}
