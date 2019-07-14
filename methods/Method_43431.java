@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  return getBleutradeDepositAddress(currency.toString()).getAddress();
}
