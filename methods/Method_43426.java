public static CurrencyPair toCurrencyPair(String pairString){
  String[] currencies=pairString.split("_");
  return new CurrencyPair(currencies[0],currencies[1]);
}
