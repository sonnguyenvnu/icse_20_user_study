public CoingiUserTransactionList getTransactions(CoingiTransactionHistoryRequest request) throws IOException {
  handleAuthentication(request);
  return coingiAuthenticated.getTransactionHistory(request);
}
