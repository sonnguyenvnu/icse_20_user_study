public GeminiTradeResponse[] getGeminiTradeHistory(String symbol,long timestamp,Integer limit) throws IOException {
  try {
    GeminiTradeResponse[] trades=gemini.pastTrades(apiKey,payloadCreator,signatureCreator,new GeminiPastTradesRequest(String.valueOf(exchange.getNonceFactory().createValue()),symbol,timestamp,limit));
    return trades;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
