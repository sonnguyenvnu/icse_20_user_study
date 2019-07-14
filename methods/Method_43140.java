public BitfinexOfferStatusResponse cancelBitfinexOffer(String offerId) throws IOException {
  BitfinexOfferStatusResponse cancelResponse=bitfinex.cancelOffer(apiKey,payloadCreator,signatureCreator,new BitfinexCancelOfferRequest(String.valueOf(exchange.getNonceFactory().createValue()),Long.valueOf(offerId)));
  return cancelResponse;
}
