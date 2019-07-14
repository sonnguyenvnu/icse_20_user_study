public boolean cancelGeminiOrder(String orderId) throws IOException {
  try {
    gemini.cancelOrders(apiKey,payloadCreator,signatureCreator,new GeminiCancelOrderRequest(String.valueOf(exchange.getNonceFactory().createValue()),Long.valueOf(orderId)));
    return true;
  }
 catch (  GeminiException e) {
    if (e.getMessage().equals("Order could not be cancelled.")) {
      return false;
    }
 else {
      throw handleException(e);
    }
  }
}
