public BTCMarketsPlaceOrderResponse placeBTCMarketsOrder(CurrencyPair currencyPair,BigDecimal amount,BigDecimal price,BTCMarketsOrder.Side side,BTCMarketsOrder.Type type) throws IOException {
  return btcm.placeOrder(exchange.getExchangeSpecification().getApiKey(),nonceFactory,this.signer,new BTCMarketsOrder(amount,price,currencyPair.counter.getCurrencyCode(),currencyPair.base.getCurrencyCode(),side,type,newReqId()));
}
