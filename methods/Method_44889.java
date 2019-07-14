private static LimitOrder adaptBid(CurrencyPair currencyPair,Order.OrderType orderType,TheRockBid theRockBid,Date timestamp){
  return new LimitOrder.Builder(orderType,currencyPair).limitPrice(theRockBid.getPrice()).originalAmount(theRockBid.getAmount()).timestamp(timestamp).build();
}
