@Override public AccountInfo getAccountInfo() throws IOException {
  return new AccountInfo(HitbtcAdapters.adaptWallet("Main",getMainBalance()),HitbtcAdapters.adaptWallet("Trading",getTradingBalance()));
}
