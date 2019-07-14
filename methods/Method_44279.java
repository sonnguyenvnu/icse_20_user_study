public List<CurrencyPair> getExchangeSymbols() throws IOException {
  try {
    List<CurrencyPair> currencyPairs=new ArrayList<>();
    for (    String symbol : gemini.getSymbols()) {
      currencyPairs.add(GeminiAdapters.adaptCurrencyPair(symbol));
    }
    return currencyPairs;
  }
 catch (  GeminiException e) {
    throw handleException(e);
  }
}
