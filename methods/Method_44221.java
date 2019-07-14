/** 
 * @return Object
 * @throws IOException
 */
public EXXOrderbook getExxOrderBook(CurrencyPair currencyPair) throws IOException {
  return exx.getOrderBook(EXXAdapters.toSymbol(currencyPair));
}
