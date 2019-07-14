public List<CCEXOrderhistory> getCCEXTradeHistory() throws IOException {
  CCEXGetorderhistoryResponse response=cCEXAuthenticated.getorderhistory(apiKey,signatureCreator,exchange.getNonceFactory());
  if (response.isSuccess()) {
    return response.getResult();
  }
 else {
    throw new ExchangeException(response.getMessage());
  }
}
