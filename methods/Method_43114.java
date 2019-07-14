public BitfinexBalancesResponse[] getBitfinexAccountInfo() throws IOException {
  BitfinexBalancesResponse[] balances=bitfinex.balances(apiKey,payloadCreator,signatureCreator,new BitfinexBalancesRequest(String.valueOf(exchange.getNonceFactory().createValue())));
  return balances;
}
