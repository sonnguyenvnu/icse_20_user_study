public BithumbAccount getBithumbAddress() throws IOException {
  final BithumbResponse<BithumbAccount> account=bithumbAuthenticated.getAccount(apiKey,signatureCreator,exchange.getNonceFactory(),"2",endpointGenerator);
  return account.getData();
}
