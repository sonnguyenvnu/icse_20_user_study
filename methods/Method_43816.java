public List<CoingiTransaction> getTransactions(CurrencyPair currencyPair,int maxCount) throws IOException {
  return coingi.getTransaction(CoingiAdapters.adaptCurrency(currencyPair),maxCount);
}
