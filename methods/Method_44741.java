public HashMap<String,PoloniexBalance> getExchangeWallet() throws IOException {
  return poloniexAuthenticated.returnCompleteBalances(apiKey,signatureCreator,exchange.getNonceFactory(),null);
}
