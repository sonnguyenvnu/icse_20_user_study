public static CurrencyPair adaptMarket(String market){
  String[] parts=market.split("_");
  return new CurrencyPair(Currency.getInstance(parts[0]),Currency.getInstance(parts[1]));
}
