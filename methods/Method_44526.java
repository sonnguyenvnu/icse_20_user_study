public LakeBTCTradeResponse[] getLakeBTCTradeHistory(long timestamp) throws IOException {
  try {
    return lakeBTCAuthenticated.pastTrades(signatureCreator,exchange.getNonceFactory(),new LakeBTCTradesRequest(String.valueOf(timestamp)));
  }
 catch (  IOException e) {
    throw new ExchangeException("LakeBTC returned an error",e);
  }
}
