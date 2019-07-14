@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  return anxRequestDepositAddress(currency.toString()).getAddress();
}
