/** 
 * ??????????
 * @param currencyPairs
 * @return
 * @throws IOException
 */
public Map<String,BitZCurrencyRate> getCurrencyRate(CurrencyPair... currencyPairs) throws IOException {
  List<String> symbolList=new ArrayList<>(currencyPairs.length);
  Arrays.stream(currencyPairs).forEach(currencyPair -> symbolList.add(BitZUtils.toPairString(currencyPair)));
  String symbols=symbolList.stream().collect(Collectors.joining(","));
  return bitz.getCurrencyRate(symbols).getData();
}
