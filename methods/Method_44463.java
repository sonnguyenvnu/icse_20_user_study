public Map<String,KrakenOrder> queryKrakenOrders(String... transactionIds) throws IOException {
  return queryKrakenOrders(false,null,transactionIds);
}
