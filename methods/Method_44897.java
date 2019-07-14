@Override public AccountInfo getAccountInfo() throws ExchangeException, NotAvailableFromExchangeException, NotYetImplementedForExchangeException, IOException {
  return new AccountInfo(UpbitAdapters.adaptWallet(super.getWallet()));
}
