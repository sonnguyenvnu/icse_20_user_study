public boolean cancelBitfinexOrder(String orderId) throws IOException {
  try {
    bitfinex.cancelOrders(apiKey,payloadCreator,signatureCreator,new BitfinexCancelOrderRequest(String.valueOf(exchange.getNonceFactory().createValue()),Long.valueOf(orderId)));
    return true;
  }
 catch (  BitfinexException e) {
    if (e.getMessage().equals("Order could not be cancelled.")) {
      return false;
    }
 else {
      throw e;
    }
  }
}
