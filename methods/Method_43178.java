public BitflyerChildOrderAcceptance sendChildOrder(LimitOrder limitOrder) throws IOException {
  BitflyerChildOrder.BitflyerChildOrderBuilder orderBuilder=BitflyerChildOrder.getOrderBuilder().withProductCode(limitOrder.getCurrencyPair()).withChildOrderType(BitflyerChildOrderType.LIMIT).withSide(limitOrder.getType()).withPrice(limitOrder.getLimitPrice()).withSize(limitOrder.getOriginalAmount());
  try {
    return bitflyer.sendChildOrder(apiKey,exchange.getNonceFactory(),signatureCreator,orderBuilder.buildOrder());
  }
 catch (  BitflyerException e) {
    throw handleError(e);
  }
}
