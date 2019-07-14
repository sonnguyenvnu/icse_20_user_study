public RippleAccountBalances getRippleAccountBalances(final String address) throws IOException {
  return ripplePublic.getAccountBalances(address);
}
