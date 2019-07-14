public static String toPairString(CurrencyPair currencyPair){
  if (currencyPair == null) {
    return null;
  }
  return adaptXchangeCurrency(currencyPair.base) + adaptXchangeCurrency(currencyPair.counter);
}
