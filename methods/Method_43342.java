public static CurrencyPair toCurrencyPair(String pairString){
  String[] pairStringSplit=pairString.split("-");
  return new CurrencyPair(pairStringSplit[1],pairStringSplit[0]);
}
