/** 
 * Get the order
 * @param currencyPair
 * @return
 * @throws IOException
 */
public BitZTrades getOrder(CurrencyPair currencyPair) throws IOException {
  BitZTradesResult result=bitz.getOrder(BitZUtils.toPairString(currencyPair));
  return result.getData();
}
