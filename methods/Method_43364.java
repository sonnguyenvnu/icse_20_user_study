public List<BittrexWithdrawalHistory> getWithdrawalsHistory(Currency currency) throws IOException {
  return bittrexAuthenticated.getwithdrawalhistory(apiKey,signatureCreator,exchange.getNonceFactory(),currency == null ? null : currency.getCurrencyCode()).getResult();
}
