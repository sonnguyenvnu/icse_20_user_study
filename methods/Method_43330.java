public BitstampOrderStatusResponse getBitstampOrder(Long orderId) throws IOException {
  try {
    return bitstampAuthenticated.getOrderStatus(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),orderId);
  }
 catch (  BitstampException e) {
    throw handleError(e);
  }
}
