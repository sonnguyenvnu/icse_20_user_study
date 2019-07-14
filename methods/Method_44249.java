@Override public String requestDepositAddress(Currency currency,String... args) throws IOException {
  return super.getGateioDepositAddress(currency).getBaseAddress();
}
