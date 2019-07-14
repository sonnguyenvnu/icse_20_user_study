public Map<String,KrakenTrade> queryKrakenTrades(String... transactionIds) throws IOException {
  return queryKrakenTrades(false,transactionIds);
}
