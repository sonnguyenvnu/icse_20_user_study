public HitbtcOrder placeLimitOrderRaw(LimitOrder limitOrder,HitbtcTimeInForce timeInForce) throws IOException {
  String symbol=HitbtcAdapters.adaptCurrencyPair(limitOrder.getCurrencyPair());
  String side=HitbtcAdapters.getSide(limitOrder.getType()).toString();
  String clientOrderId=null;
  if (limitOrder instanceof HitbtcLimitOrder) {
    HitbtcLimitOrder order=(HitbtcLimitOrder)limitOrder;
    clientOrderId=order.getClientOrderId();
  }
  return hitbtc.postHitbtcNewOrder(clientOrderId,symbol,side,limitOrder.getLimitPrice(),limitOrder.getOriginalAmount(),HitbtcOrderType.limit,timeInForce);
}
