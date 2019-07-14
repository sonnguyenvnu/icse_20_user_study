@Override public AccountInfo getAccountInfo() throws IOException {
  final BitcoinCoreBalanceResponse balance=getBalance();
  final BitcoinCoreBalanceResponse unconfirmed=getUnconfirmedBalance();
  return BitcoinCoreAdapters.adaptAccountInfo(balance,unconfirmed);
}
