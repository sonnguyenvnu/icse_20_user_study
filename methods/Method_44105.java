public static LimitOrder createLimitOrder(MarketOrder marketOrder,DSXExchangeInfo dsxExchangeInfo){
  DSXPairInfo dsxPairInfo=dsxExchangeInfo.getPairs().get(currencyPairToMarketName(marketOrder.getCurrencyPair()));
  BigDecimal limitPrice=marketOrder.getType() == OrderType.BID ? dsxPairInfo.getMaxPrice() : dsxPairInfo.getMinPrice();
  return LimitOrder.Builder.from(marketOrder).limitPrice(limitPrice).build();
}
