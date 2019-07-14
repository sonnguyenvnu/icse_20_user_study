public TheRockTransactions withdrawls(Currency currency,Date after,Date before,Integer page) throws IOException {
  return theRockAuthenticated.transactions(apiKey,signatureCreator,exchange.getNonceFactory(),"withdraw",after,before,currency == null ? null : currency.getCurrencyCode(),page);
}
