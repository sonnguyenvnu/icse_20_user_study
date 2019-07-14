public void verifyOrder(LimitOrder limitOrder){
  ExchangeMetaData exchangeMetaData=exchange.getExchangeMetaData();
  verifyOrder(limitOrder,exchangeMetaData);
  BigDecimal price=limitOrder.getLimitPrice().stripTrailingZeros();
  if (price.scale() > exchangeMetaData.getCurrencyPairs().get(limitOrder.getCurrencyPair()).getPriceScale()) {
    throw new IllegalArgumentException("Unsupported price scale " + price.scale());
  }
}
