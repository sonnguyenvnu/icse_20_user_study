/** 
 * ????????????
 * @param currencyPairs
 * @return
 * @throws IOException
 */
public Map<Currency,Map<Currency,BigDecimal>> getCurrencyCoinRate(CurrencyPair... currencyPairs) throws IOException {
  List<String> coinList=new ArrayList<>(currencyPairs.length);
  Arrays.stream(currencyPairs).forEach(currencyPair -> coinList.add(currencyPair.base.getCurrencyCode().toLowerCase()));
  String coins=coinList.stream().collect(Collectors.joining(","));
  return bitz.getCurrencyCoinRate(coins).getData();
}
