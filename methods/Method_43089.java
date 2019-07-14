public boolean cancel(String orderId) throws IOException {
  HashMap<String,String> response=bitcointoyouAuthenticated.deleteOrder(apiKey,exchange.getNonceFactory(),signatureCreator,orderId);
  if (response.containsKey("error")) {
    throw new ExchangeException(response.get("error"));
  }
  return response.get("success").equals("1");
}
