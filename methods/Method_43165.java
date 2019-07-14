@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  List<BitflyerAddress> addresses=getAddresses();
  for (  BitflyerAddress address : addresses) {
    if (address.getCurrencyCode().equals(currency.getCurrencyCode()))     return address.getAddress();
  }
  throw new NotAvailableFromExchangeException();
}
