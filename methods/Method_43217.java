@Override public String requestDepositAddress(Currency currency,String... strings) throws IOException {
  BitMarketDepositResponse response=depositToBitMarket(currency.toString());
  return response.getData();
}
