public BTCMarketsBaseResponse cancelBTCMarketsOrder(Long orderId) throws IOException {
  return btcm.cancelOrder(exchange.getExchangeSpecification().getApiKey(),nonceFactory,signer,new BTCMarketsCancelOrderRequest(orderId));
}
