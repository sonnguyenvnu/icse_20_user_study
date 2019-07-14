public BitfinexBalanceHistoryResponse[] getBitfinexBalanceHistory(String currency,String wallet,Long since,Long until,int limit) throws IOException {
  return bitfinex.balanceHistory(apiKey,payloadCreator,signatureCreator,new BitfinexBalanceHistoryRequest(String.valueOf(exchange.getNonceFactory().createValue()),currency,since,until,limit,wallet));
}
