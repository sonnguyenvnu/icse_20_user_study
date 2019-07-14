/** 
 * Get market depth from exchange
 * @param pairs String of currency pairs to retrieve (e.g. "btcusd-btceur")
 * @return DSXOrderbookWrapper object
 * @throws IOException
 */
public DSXOrderbookWrapper getDSXOrderbook(String pairs,String type) throws IOException {
  return dsx.getOrderbook(pairs.toLowerCase(),1,type);
}
