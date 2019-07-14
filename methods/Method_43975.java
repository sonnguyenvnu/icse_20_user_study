public static CurrencyPair getCurrencyPairFromString(String currencyPairString){
  if (currencyPairString == null || currencyPairString.isEmpty()) {
    return null;
  }
  final boolean isMixedCase=currencyPairString.matches(".*[a-z]+.*") && currencyPairString.matches(".*[A-Z]+.*");
  if (!isMixedCase) {
    currencyPairString=currencyPairString.toUpperCase();
  }
  final String symbols[]=currencyPairString.split("[^a-zA-Z0-9]");
  if (symbols.length == 2) {
    return new CurrencyPair(symbols[0],symbols[1]);
  }
  if (currencyPairString.length() == 6) {
    final String tradeCurrency=currencyPairString.substring(0,3);
    final String priceCurrency=currencyPairString.substring(3);
    return new CurrencyPair(tradeCurrency,priceCurrency);
  }
  int bestGuess=currencyPairString.length() / 2;
  int bestLength=0;
  for (int i=1; i < currencyPairString.length() - 1; ++i) {
    final Currency tradeCurrency=Currency.getInstanceNoCreate(currencyPairString.substring(0,i));
    final Currency priceCurrency=Currency.getInstanceNoCreate(currencyPairString.substring(i));
    if (tradeCurrency != null) {
      if (priceCurrency != null) {
        return new CurrencyPair(tradeCurrency,priceCurrency);
      }
 else       if (i > bestLength) {
        bestLength=i;
        bestGuess=i;
      }
    }
 else     if (priceCurrency != null && currencyPairString.length() - i > bestLength) {
      bestLength=currencyPairString.length() - i;
      bestGuess=i;
    }
  }
  final String tradeCurrency=currencyPairString.substring(0,bestGuess);
  final String priceCurrency=currencyPairString.substring(bestGuess);
  return new CurrencyPair(tradeCurrency,priceCurrency);
}
