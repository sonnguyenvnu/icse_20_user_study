public BitfinexAccountInfosResponse[] getBitfinexAccountInfos() throws IOException {
  return bitfinex.accountInfos(apiKey,payloadCreator,signatureCreator,new BitfinexNonceOnlyRequest("/v1/account_infos",String.valueOf(exchange.getNonceFactory().createValue())));
}
