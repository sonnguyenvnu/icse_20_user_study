/** 
 * Get Kline data
 * @param currencyPair
 * @param resolution
 * @param size
 * @param microsecond
 * @return
 * @throws IOException
 */
public BitZKline getKline(CurrencyPair currencyPair,BitZKlineResolution resolution,Integer size,String microsecond) throws IOException {
  return bitz.getKline(BitZUtils.toPairString(currencyPair),resolution.code(),size,microsecond).getData();
}
