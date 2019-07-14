@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  try {
    return getDepositAddress(currency.toString());
  }
 catch (  PoloniexException e) {
    throw PoloniexErrorAdapter.adapt(e);
  }
}
