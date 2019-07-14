@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  return getCCEXDepositAddress(currency.toString().toUpperCase());
}
