public GeminiOrderStatusResponse getGeminiOrderStatus(String orderId) throws IOException {
  try {
    GeminiOrderStatusResponse orderStatus=gemini.orderStatus(apiKey,payloadCreator,signatureCreator,new GeminiOrderStatusRequest(String.valueOf(exchange.getNonceFactory().createValue()),Long.valueOf(orderId)));
    return orderStatus;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
