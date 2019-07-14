public List<CCEXBalance> getCCEXAccountInfo() throws IOException {
  CCEXBalancesResponse response=cCEXAuthenticated.balances(apiKey,signatureCreator,exchange.getNonceFactory());
  if (response.isSuccess()) {
    return response.getResult();
  }
 else {
    throw new ExchangeException(response.getMessage());
  }
}
