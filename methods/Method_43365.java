public List<BittrexDepositHistory> getDepositsHistory(Currency currency) throws IOException {
  return bittrexAuthenticated.getdeposithistory(apiKey,signatureCreator,exchange.getNonceFactory(),currency == null ? null : currency.getCurrencyCode()).getResult();
}
