public static CurrencyPair toCurrencyPair(String symbol){
  int token=symbol.indexOf('-');
  String left=symbol.substring(0,token);
  String right=symbol.substring(token + 1);
  return new CurrencyPair(left,right);
}
