public CoinoneBalancesResponse getWallet() throws CoinoneException, IOException {
  CoinoneBalancesRequest request=new CoinoneBalancesRequest(apiKey,exchange.getNonceFactory().createValue());
  CoinoneBalancesResponse response=coinone.getWallet(payloadCreator,signatureCreator,request);
  return response;
}
