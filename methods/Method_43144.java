public BitfinexActivePositionsResponse[] getBitfinexActivePositions() throws IOException {
  BitfinexActivePositionsResponse[] activePositions=bitfinex.activePositions(apiKey,payloadCreator,signatureCreator,new BitfinexNonceOnlyRequest("/v1/positions",String.valueOf(exchange.getNonceFactory().createValue())));
  return activePositions;
}
