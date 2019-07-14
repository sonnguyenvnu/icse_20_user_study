@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  long orderId=trade(OkCoinAdapters.adaptSymbol(limitOrder.getCurrencyPair()),limitOrder.getType() == OrderType.BID ? "buy" : "sell",limitOrder.getLimitPrice().toPlainString(),limitOrder.getOriginalAmount().toPlainString()).getOrderId();
  return String.valueOf(orderId);
}
