@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  return depositAddresses().get(currency.getCurrencyCode());
}
