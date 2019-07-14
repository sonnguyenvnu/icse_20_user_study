protected void handleAuthentication(Object obj){
  if (obj instanceof CoingiAuthenticatedRequest) {
    CoingiAuthenticatedRequest request=(CoingiAuthenticatedRequest)obj;
    Long nonce=exchange.getNonceFactory().createValue();
    request.setToken(exchange.getExchangeSpecification().getApiKey());
    request.setNonce(nonce);
    request.setSignature(signatureCreator.sign(nonce));
  }
}
