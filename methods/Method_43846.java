@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  CoinmateDepositAddresses addresses=coinmateBitcoinDepositAddresses();
  if (addresses.getData().isEmpty()) {
    return null;
  }
 else {
    return addresses.getData().get(0);
  }
}
