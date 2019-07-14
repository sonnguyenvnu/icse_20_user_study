public GeminiLend[] getGeminiLends(String currency,long sinceTimestamp,int limitTrades) throws IOException {
  try {
    GeminiLend[] GeminiLends=gemini.getLends(currency,sinceTimestamp,limitTrades);
    return GeminiLends;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
