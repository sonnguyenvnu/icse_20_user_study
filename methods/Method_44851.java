private void validate(Order order){
  if (order.getOriginalAmount().compareTo(minimumAmount) < 0) {
    throw new ExchangeException("Trade amount is " + order.getOriginalAmount() + ", minimum is " + minimumAmount);
  }
  if (order instanceof LimitOrder) {
    LimitOrder limitOrder=(LimitOrder)order;
    if (limitOrder.getLimitPrice() == null) {
      throw new ExchangeException("No price");
    }
    if (limitOrder.getLimitPrice().compareTo(ZERO) <= 0) {
      throw new ExchangeException("Limit price is " + limitOrder.getLimitPrice() + ", must be positive");
    }
    int scale=limitOrder.getLimitPrice().stripTrailingZeros().scale();
    if (scale > priceScale) {
      throw new ExchangeException("Price scale is " + scale + ", maximum is " + priceScale);
    }
  }
}
