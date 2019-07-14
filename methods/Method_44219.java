@Override public AccountInfo getAccountInfo() throws IOException {
  return EXXAdapters.convertBalance(super.getExxAccountInfo());
}
