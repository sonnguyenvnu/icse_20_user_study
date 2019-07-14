public KrakenOrderResponse placeKrakenMarketOrder(MarketOrder marketOrder) throws IOException {
  KrakenType type=KrakenType.fromOrderType(marketOrder.getType());
  KrakenOrderBuilder orderBuilder=KrakenStandardOrder.getMarketOrderBuilder(marketOrder.getCurrencyPair(),type,marketOrder.getOriginalAmount()).withOrderFlags(marketOrder.getOrderFlags()).withLeverage(marketOrder.getLeverage());
  return placeKrakenOrder(orderBuilder.buildOrder());
}
