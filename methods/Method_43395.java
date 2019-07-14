/** 
 * Get depth data
 * @param currencyPair
 * @throws IOException
 */
public BitZOrders getDepth(CurrencyPair currencyPair) throws IOException {
  BitZOrdersResult result=bitz.getDepth(BitZUtils.toPairString(currencyPair));
  return result.getData();
}
