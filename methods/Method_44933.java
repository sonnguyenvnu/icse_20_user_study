public static String getPair(CurrencyPair currencyPair){
  String base=adaptCurrencyOut(currencyPair.base);
  String counter=adaptCurrencyOut(currencyPair.counter);
  return (base + "_" + counter);
}
