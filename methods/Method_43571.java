public List<CCEXOpenorder> getCCEXOpenOrders() throws IOException {
  CCEXGetopenordersResponse response=cCEXAuthenticated.getopenorders(apiKey,signatureCreator,exchange.getNonceFactory());
  if (response.isSuccess()) {
    return response.getResult();
  }
 else {
    throw new ExchangeException(response.getMessage());
  }
}
