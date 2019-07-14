public CryptoFacilitiesCancel cancelCryptoFacilitiesOrder(String uid) throws IOException {
  CryptoFacilitiesCancel res=cryptoFacilities.cancelOrder(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),uid);
  if (res.isSuccess()) {
    return res;
  }
 else {
    throw new ExchangeException("Error cancelling CF order: " + res.getError());
  }
}
