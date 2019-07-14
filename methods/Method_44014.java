public CryptoFacilitiesOrder sendCryptoFacilitiesLimitOrder(LimitOrder order) throws IOException {
  String orderType=order.hasFlag(CryptoFacilitiesOrderFlags.POST_ONLY) ? "post" : "lmt";
  String symbol=order.getCurrencyPair().base.toString();
  String side="buy";
  if (order.getType().equals(OrderType.ASK)) {
    side="sell";
  }
  BigDecimal size=order.getOriginalAmount();
  BigDecimal limitPrice=order.getLimitPrice();
  CryptoFacilitiesOrder ord=cryptoFacilities.sendOrder(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),orderType,symbol,side,size,limitPrice);
  if (ord.isSuccess()) {
    return ord;
  }
 else {
    throw new ExchangeException("Error sending CF limit order: " + ord.getError());
  }
}
