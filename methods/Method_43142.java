public BitfinexTradeResponse[] getBitfinexTradeHistory(String symbol,long startTime,Long endTime,Integer limit,Integer reverse) throws IOException {
  BitfinexTradeResponse[] trades=bitfinex.pastTrades(apiKey,payloadCreator,signatureCreator,new BitfinexPastTradesRequest(String.valueOf(exchange.getNonceFactory().createValue()),symbol,startTime,endTime,limit,reverse));
  return trades;
}
