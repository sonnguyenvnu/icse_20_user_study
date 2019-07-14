@Override public String requestDepositAddress(Currency currency,String... arguments) throws IOException {
  try {
    final BitfinexDepositAddressResponse response=super.requestDepositAddressRaw(currency.getCurrencyCode());
    return response.getAddress();
  }
 catch (  BitfinexException e) {
    throw BitfinexErrorAdapter.adapt(e);
  }
}
