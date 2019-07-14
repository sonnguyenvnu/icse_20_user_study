public TheRockTransactions deposits(Currency currency,Date after,Date before,Integer page) throws IOException {
  return theRockAuthenticated.transactions(apiKey,signatureCreator,exchange.getNonceFactory(),"atm_payment",after,before,currency == null ? null : currency.getCurrencyCode(),page);
}
