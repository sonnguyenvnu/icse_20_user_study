public HashMap<String,PoloniexLoan[]> getLoanInfo() throws IOException {
  return poloniexAuthenticated.returnActiveLoans(apiKey,signatureCreator,exchange.getNonceFactory());
}
