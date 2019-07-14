public List<BithumbTransactionHistory> getBithumbTrades(CurrencyPair currencyPair) throws IOException {
  final BithumbResponse<List<BithumbTransactionHistory>> transactionHistory=bithumb.transactionHistory(BithumbUtils.getBaseCurrency(currencyPair));
  return transactionHistory.getData();
}
