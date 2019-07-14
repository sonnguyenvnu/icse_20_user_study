@Override public String placeStopOrder(StopOrder stopOrder) throws IOException {
  CoinmateTradeResponse response;
  boolean hidden=stopOrder.getOrderFlags().contains(CoinmateOrderFlags.HIDDEN);
  boolean immediateOrCancel=stopOrder.getOrderFlags().contains(CoinmateOrderFlags.IMMEDIATE_OR_CANCEL);
  boolean trailing=stopOrder.getOrderFlags().contains(CoinmateOrderFlags.TRAILING);
  if (stopOrder.getType().equals(Order.OrderType.ASK)) {
    response=sellCoinmateLimit(stopOrder.getOriginalAmount(),stopOrder.getLimitPrice(),CoinmateUtils.getPair(stopOrder.getCurrencyPair()),stopOrder.getStopPrice(),hidden ? 1 : 0,immediateOrCancel ? 1 : 0,trailing ? 1 : 0);
  }
 else   if (stopOrder.getType().equals(Order.OrderType.BID)) {
    response=buyCoinmateLimit(stopOrder.getOriginalAmount(),stopOrder.getLimitPrice(),CoinmateUtils.getPair(stopOrder.getCurrencyPair()),stopOrder.getStopPrice(),hidden ? 1 : 0,immediateOrCancel ? 1 : 0,trailing ? 1 : 0);
  }
 else {
    throw new CoinmateException("Unknown order type");
  }
  return Long.toString(response.getData());
}
