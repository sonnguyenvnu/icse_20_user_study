public Map balanceHistory(BitbayBalancesHistoryQuery query) throws IOException {
  String jsonQuery=ObjectMapperHelper.toCompactJSON(query);
  return bitbayAuthenticated.balanceHistory(apiKey,sign,exchange.getNonceFactory(),UUID.randomUUID(),jsonQuery);
}
