public BitfinexCreditResponse[] getBitfinexActiveCredits() throws IOException {
  BitfinexCreditResponse[] credits=bitfinex.activeCredits(apiKey,payloadCreator,signatureCreator,new BitfinexActiveCreditsRequest(String.valueOf(exchange.getNonceFactory().createValue())));
  return credits;
}
