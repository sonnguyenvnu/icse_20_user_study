private static LimitOrder createOrder(BitMarketOrder bitMarketOrder){
  return new LimitOrder(bitMarketOrder.getType(),bitMarketOrder.getAmount(),bitMarketOrder.getCurrencyPair(),String.valueOf(bitMarketOrder.getId()),bitMarketOrder.getTimestamp(),bitMarketOrder.getRate());
}
