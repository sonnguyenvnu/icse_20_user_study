@JsonAnySetter public void setOrderBooksByPair(String pair,LivecoinOrderBook orderBook){
  orderBooksByPair.put(pair,orderBook);
}
