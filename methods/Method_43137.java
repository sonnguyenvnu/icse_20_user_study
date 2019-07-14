public BitfinexOfferStatusResponse placeBitfinexFixedRateLoanOrder(FixedRateLoanOrder loanOrder,BitfinexOrderType orderType) throws IOException {
  String direction=loanOrder.getType() == OrderType.BID ? "loan" : "lend";
  BitfinexOfferStatusResponse newOrderResponse=bitfinex.newOffer(apiKey,payloadCreator,signatureCreator,new BitfinexNewOfferRequest(String.valueOf(exchange.getNonceFactory().createValue()),loanOrder.getCurrency(),loanOrder.getOriginalAmount(),loanOrder.getRate(),loanOrder.getDayPeriod(),direction));
  return newOrderResponse;
}
