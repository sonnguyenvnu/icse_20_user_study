@Override public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
  String type=marketOrder.getType().equals(Order.OrderType.BID) ? "market_buy" : "market_sell";
  return placeOrder(type,BigDecimal.ZERO,marketOrder.getCurrencyPair(),marketOrder.getOriginalAmount());
}
