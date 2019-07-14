public GeminiLendDepth getGeminiLendBook(String currency,int limitBids,int limitAsks) throws IOException {
  try {
    GeminiLendDepth GeminiLendDepth=gemini.getLendBook(currency,limitBids,limitAsks);
    return GeminiLendDepth;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
