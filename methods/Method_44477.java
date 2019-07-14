public List<AccountBalancesResponse> getKucoinAccounts() throws IOException {
  checkAuthenticated();
  return classifyingExceptions(() -> accountApi.getAccountList(apiKey,digest,nonceFactory,passphrase,null,null));
}
