public static AbucoinsCreateMarketOrderRequest adaptAbucoinsCreateMarketOrderRequest(MarketOrder marketOrder){
  return new AbucoinsCreateMarketOrderRequest(adaptAbucoinsSide(marketOrder.getType()),adaptCurrencyPairToProductID(marketOrder.getCurrencyPair()),marketOrder.getOriginalAmount(),null);
}
