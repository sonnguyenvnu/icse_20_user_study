public HashMap<String,PoloniexBalance> getWallets() throws IOException {
  return poloniexAuthenticated.returnCompleteBalances(apiKey,signatureCreator,exchange.getNonceFactory(),"all");
}
