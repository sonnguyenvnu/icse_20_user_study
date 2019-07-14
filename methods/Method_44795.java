public List<QuoineTransaction> withdrawalHistory(Currency currency,Integer limit,Integer page) throws IOException {
  QuoineTransactionsResponse response=quoine.transactions(QUOINE_API_VERSION,signatureCreator,contentType,currency.getCurrencyCode(),"withdrawal",limit,page);
  return response.models;
}
