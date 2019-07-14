@Override public String requestDepositAddress(Currency currency,String... strings) throws IOException {
  return getDepositAddress(currency.toString());
}
