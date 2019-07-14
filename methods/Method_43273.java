@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  String currencyCode=currency.getCurrencyCode();
  if (currencyCode.equals("XBT")) {
    currencyCode="XBt";
  }
  return requestDepositAddress(currencyCode);
}
