public BitfinexOrderStatusResponse[] getBitfinexOpenOrders() throws IOException {
  BitfinexOrderStatusResponse[] activeOrders=bitfinex.activeOrders(apiKey,payloadCreator,signatureCreator,new BitfinexNonceOnlyRequest("/v1/orders",String.valueOf(exchange.getNonceFactory().createValue())));
  return activeOrders;
}
