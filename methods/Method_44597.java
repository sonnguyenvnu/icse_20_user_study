@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  LunoPostOrder postLimitOrder=lunoAPI.postLimitOrder(LunoUtil.toLunoPair(limitOrder.getCurrencyPair()),convertForLimit(limitOrder.getType()),limitOrder.getOriginalAmount(),limitOrder.getLimitPrice(),null,null);
  return postLimitOrder.orderId;
}
