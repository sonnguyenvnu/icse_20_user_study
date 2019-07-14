@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  BaseYoBitResponse response=getDepositAddress(currency);
  if (!response.success)   throw new ExchangeException("failed to withdraw funds");
  return response.returnData.get("address").toString();
}
