@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  try {
    return getBithumbWalletAddress(currency).map(BithumbWalletAddress::getWalletAddress).orElse(null);
  }
 catch (  BithumbException e) {
    throw BithumbErrorAdapter.adapt(e);
  }
}
