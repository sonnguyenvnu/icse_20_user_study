public GeminiDepth getGeminiOrderBook(String pair,Integer limitBids,Integer limitAsks) throws IOException {
  try {
    GeminiDepth GeminiDepth;
    if (limitBids == null && limitAsks == null) {
      GeminiDepth=gemini.getBook(pair);
    }
 else {
      GeminiDepth=gemini.getBook(pair,limitBids,limitAsks);
    }
    return GeminiDepth;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
