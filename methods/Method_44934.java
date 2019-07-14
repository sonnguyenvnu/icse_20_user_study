public static LimitOrder createLimitOrder(MarketOrder marketOrder,WexExchangeInfo wexExchangeInfo){
  WexPairInfo wexPairInfo=wexExchangeInfo.getPairs().get(getPair(marketOrder.getCurrencyPair()));
  BigDecimal limitPrice=marketOrder.getType() == OrderType.BID ? wexPairInfo.getMaxPrice() : wexPairInfo.getMinPrice();
  return LimitOrder.Builder.from(marketOrder).limitPrice(limitPrice).build();
}
