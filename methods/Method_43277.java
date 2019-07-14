public String requestDepositAddress(String currency) throws ExchangeException {
  try {
    return bitmex.getDepositAddress(apiKey,exchange.getNonceFactory(),signatureCreator,currency);
  }
 catch (  IOException e) {
    throw handleError(e);
  }
}
