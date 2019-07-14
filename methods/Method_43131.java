public BitfinexOfferStatusResponse[] getBitfinexOpenOffers() throws IOException {
  BitfinexOfferStatusResponse[] activeOffers=bitfinex.activeOffers(apiKey,payloadCreator,signatureCreator,new BitfinexNonceOnlyRequest("/v1/offers",String.valueOf(exchange.getNonceFactory().createValue())));
  return activeOffers;
}
