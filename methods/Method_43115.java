public BitfinexMarginInfosResponse[] getBitfinexMarginInfos() throws IOException {
  BitfinexMarginInfosResponse[] marginInfos=bitfinex.marginInfos(apiKey,payloadCreator,signatureCreator,new BitfinexMarginInfosRequest(String.valueOf(exchange.getNonceFactory().createValue())));
  return marginInfos;
}
