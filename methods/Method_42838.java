public static AbucoinsCreateLimitOrderRequest adaptAbucoinsCreateLimitOrderRequest(LimitOrder limitOrder){
  return new AbucoinsCreateLimitOrderRequest(adaptAbucoinsSide(limitOrder.getType()),adaptCurrencyPairToProductID(limitOrder.getCurrencyPair()),null,null,limitOrder.getLimitPrice(),limitOrder.getOriginalAmount(),AbucoinsOrder.TimeInForce.GTC,null,null);
}
