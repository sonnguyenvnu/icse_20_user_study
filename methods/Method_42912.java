@Override public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
  Assert.notNull(limitOrder.getLimitPrice(),"getLimitPrice() cannot be null");
  Assert.notNull(limitOrder.getOriginalAmount(),"getOriginalAmount() cannot be null");
  if (limitOrder.getOriginalAmount().scale() > 8) {
    throw new IllegalArgumentException("originalAmount scale exceeds max");
  }
  if (limitOrder.getLimitPrice().scale() > ANXUtils.getMaxPriceScale(limitOrder.getCurrencyPair())) {
    throw new IllegalArgumentException("price scale exceeds max");
  }
  String type=limitOrder.getType().equals(OrderType.BID) ? "bid" : "ask";
  BigDecimal amount=limitOrder.getOriginalAmount();
  BigDecimal price=limitOrder.getLimitPrice();
  return placeANXLimitOrder(limitOrder.getCurrencyPair(),type,amount,price).getDataString();
}
