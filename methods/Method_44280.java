public GeminiOrderStatusResponse[] getGeminiOpenOrders() throws IOException {
  try {
    GeminiOrderStatusResponse[] activeOrders=gemini.activeOrders(apiKey,payloadCreator,signatureCreator,new GeminiNonceOnlyRequest("/v1/orders",String.valueOf(exchange.getNonceFactory().createValue())));
    return activeOrders;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
