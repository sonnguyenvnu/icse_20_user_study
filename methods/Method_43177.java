public BitflyerChildOrderAcceptance sendChildOrder(MarketOrder marketOrder) throws IOException {
  BitflyerChildOrder.BitflyerChildOrderBuilder orderBuilder=BitflyerChildOrder.getOrderBuilder().withProductCode(marketOrder.getCurrencyPair()).withChildOrderType(BitflyerChildOrderType.MARKET).withSide(marketOrder.getType()).withSize(marketOrder.getOriginalAmount());
  try {
    return bitflyer.sendChildOrder(apiKey,exchange.getNonceFactory(),signatureCreator,orderBuilder.buildOrder());
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
