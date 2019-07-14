/** 
 * ??OKEx??????
 * @param currencyPair
 * @return
 * @throws IOException
 */
public OkCoinFutureComment getFuturesIndex(CurrencyPair currencyPair) throws IOException {
  return okCoin.getFuturesIndex("1",OkCoinAdapters.adaptSymbol(currencyPair));
}
