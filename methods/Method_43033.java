public BitbayUserTrades getBitbayTransactions(BitbayUserTradesQuery query) throws IOException, ExchangeException {
  String jsonQuery=ObjectMapperHelper.toJSON(query);
  jsonQuery=WHITESPACES.matcher(jsonQuery).replaceAll("");
  BitbayUserTrades response=bitbayAuthenticated.getTransactionHistory(apiKey,sign,exchange.getNonceFactory(),UUID.randomUUID(),jsonQuery);
  checkError(response);
  return response;
}
