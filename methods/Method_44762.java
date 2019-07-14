public HashMap<String,String> getFeeInfo() throws IOException {
  HashMap<String,String> response=poloniexAuthenticated.returnFeeInfo(apiKey,signatureCreator,exchange.getNonceFactory());
  if (response.containsKey("error")) {
    throw new PoloniexException(response.get("error"));
  }
  return response;
}
