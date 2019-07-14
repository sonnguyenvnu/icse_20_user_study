public Pagination<WithdrawalResponse> getWithdrawalsList(String currency,String status,Long startAt,Long endAt) throws IOException {
  checkAuthenticated();
  return classifyingExceptions(() -> withdrawalAPI.getWithdrawalsList(apiKey,digest,nonceFactory,passphrase,currency,status,startAt,endAt));
}
