public List<BithumbTransaction> bithumbTransactions(CurrencyPair currencyPair) throws IOException {
  final BithumbResponse<List<BithumbTransaction>> transactions=bithumbAuthenticated.getTransactions(apiKey,signatureCreator,exchange.getNonceFactory(),"2",endpointGenerator,null,null,BithumbUtils.getBaseCurrency(currencyPair));
  return transactions.getData();
}
