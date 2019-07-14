public static CurrencyPair adaptSymbol(String symbol){
  String[] currencies=symbol.toUpperCase().split("_");
  return new CurrencyPair(currencies[0],currencies[1]);
}
