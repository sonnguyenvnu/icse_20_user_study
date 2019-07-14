public List<OrderBook> getAllOrderBooks(Integer depth){
  return getOrderBooks(depth,exchange.getExchangeSymbols());
}
