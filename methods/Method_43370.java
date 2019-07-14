/** 
 * @deprecated Endpoint still valid, but Bittrex have disabled market orders. Seehttps://twitter.com/bittrexexchange/status/526590250487783425.
 */
@Deprecated public String placeBittrexMarketOrder(MarketOrder marketOrder) throws IOException {
  return (OrderType.BID.equals(marketOrder.getType()) ? bittrexAuthenticated.buymarket(apiKey,signatureCreator,exchange.getNonceFactory(),BittrexUtils.toPairString(marketOrder.getCurrencyPair()),marketOrder.getOriginalAmount().toPlainString()) : bittrexAuthenticated.sellmarket(apiKey,signatureCreator,exchange.getNonceFactory(),BittrexUtils.toPairString(marketOrder.getCurrencyPair()),marketOrder.getOriginalAmount().toPlainString())).getResult().getUuid();
}
