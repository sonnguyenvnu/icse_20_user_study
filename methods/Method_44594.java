@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  String lunoCurrency=LunoUtil.toLunoCurrency(currency);
  LunoFundingAddress lfa=lunoAPI.createFundingAddress(lunoCurrency);
  return lfa.address;
}
