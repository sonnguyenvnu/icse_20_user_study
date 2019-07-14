@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  CoinmateTradeResponse response;
  boolean hidden=limitOrder.getOrderFlags().contains(CoinmateOrderFlags.HIDDEN);
  boolean immediateOrCancel=limitOrder.getOrderFlags().contains(CoinmateOrderFlags.IMMEDIATE_OR_CANCEL);
  boolean trailing=limitOrder.getOrderFlags().contains(CoinmateOrderFlags.TRAILING);
  if (limitOrder.getType().equals(Order.OrderType.ASK)) {
    response=sellCoinmateLimit(limitOrder.getOriginalAmount(),limitOrder.getLimitPrice(),CoinmateUtils.getPair(limitOrder.getCurrencyPair()),null,hidden ? 1 : 0,immediateOrCancel ? 1 : 0,trailing ? 1 : 0);
  }
 else   if (limitOrder.getType().equals(Order.OrderType.BID)) {
    response=buyCoinmateLimit(limitOrder.getOriginalAmount(),limitOrder.getLimitPrice(),CoinmateUtils.getPair(limitOrder.getCurrencyPair()),null,hidden ? 1 : 0,immediateOrCancel ? 1 : 0,trailing ? 1 : 0);
  }
 else {
    throw new CoinmateException("Unknown order type");
  }
  return Long.toString(response.getData());
}
