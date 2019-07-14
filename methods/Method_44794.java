public List<QuoineTransaction> depositHistory(Currency currency,Integer limit,Integer page) throws IOException {
  QuoineTransactionsResponse response=quoine.transactions(QUOINE_API_VERSION,signatureCreator,contentType,currency.getCurrencyCode(),"funding",limit,page);
  return response.models;
}
