protected final void verifyOrder(Order order,ExchangeMetaData exchangeMetaData){
  CurrencyPairMetaData metaData=exchangeMetaData.getCurrencyPairs().get(order.getCurrencyPair());
  if (metaData == null) {
    throw new IllegalArgumentException("Invalid CurrencyPair");
  }
  BigDecimal originalAmount=order.getOriginalAmount();
  if (originalAmount == null) {
    throw new IllegalArgumentException("Missing originalAmount");
  }
  BigDecimal amount=originalAmount.stripTrailingZeros();
  BigDecimal minimumAmount=metaData.getMinimumAmount();
  if (minimumAmount != null) {
    if (amount.scale() > minimumAmount.scale()) {
      throw new IllegalArgumentException("Unsupported amount scale " + amount.scale());
    }
 else     if (amount.compareTo(minimumAmount) < 0) {
      throw new IllegalArgumentException("Order amount less than minimum");
    }
  }
}
