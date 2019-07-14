public HitbtcOrder placeMarketOrderRaw(MarketOrder marketOrder) throws IOException {
  String symbol=HitbtcAdapters.adaptCurrencyPair(marketOrder.getCurrencyPair());
  String side=HitbtcAdapters.getSide(marketOrder.getType()).toString();
  String clientOrderId=null;
  if (marketOrder instanceof HitbtcMarketOrder) {
    clientOrderId=((HitbtcMarketOrder)marketOrder).getClientOrderId();
  }
  return hitbtc.postHitbtcNewOrder(clientOrderId,symbol,side,null,marketOrder.getOriginalAmount(),HitbtcOrderType.market,HitbtcTimeInForce.IOC);
}
