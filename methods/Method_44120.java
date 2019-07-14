@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  return requestAddress(currency.toString(),Integer.parseInt(args[0]));
}
