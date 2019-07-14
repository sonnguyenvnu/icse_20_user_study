public static List<CurrencyPair> adaptCurrencyPair(Iterable<String> dsxPairs){
  List<CurrencyPair> pairs=new ArrayList<>();
  for (  String dsxPair : dsxPairs) {
    pairs.add(adaptCurrencyPair(dsxPair));
  }
  return pairs;
}
