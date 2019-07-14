public static LykkeLimitOrder adaptLykkeOrder(LimitOrder limitOrder){
  return new LykkeLimitOrder(adaptToAssetPair(limitOrder.getCurrencyPair()),LykkeOrderType.fromOrderType(limitOrder.getType()),limitOrder.getOriginalAmount().doubleValue(),limitOrder.getLimitPrice().doubleValue());
}
