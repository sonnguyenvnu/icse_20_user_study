public static CurrencyPair toCurrencyPair(String pair){
  String[] currencies=pair.split("_");
  return new CurrencyPair(currencies[1],currencies[0]);
}
