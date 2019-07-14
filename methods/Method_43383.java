public static CurrencyPair toCurrencyPair(String pairstring){
  String[] parts=pairstring.split("_");
  if (parts.length == 2) {
    Currency base=Currency.getInstanceNoCreate(parts[0]);
    Currency counter=Currency.getInstanceNoCreate(parts[1]);
    if (base != null && counter != null) {
      return new CurrencyPair(base,counter);
    }
  }
  return null;
}
