@Override public AccountInfo getAccountInfo() throws IOException {
  return CoinsuperAdapters.convertBalance(super.getUserAssetInfo());
}
