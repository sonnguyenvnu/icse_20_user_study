/** 
 * ??????????
 * @param currencyPairs
 * @throws IOException
 */
public BitZSymbolList getSymbolList(CurrencyPair... currencyPairs) throws IOException {
  List<String> symbolList=new ArrayList<>(currencyPairs.length);
  Arrays.stream(currencyPairs).forEach(currencyPair -> symbolList.add(BitZUtils.toPairString(currencyPair)));
  String symbols=symbolList.stream().collect(Collectors.joining(","));
  return bitz.getSymbolList(symbols).getData();
}
