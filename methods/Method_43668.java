/** 
 * Unauthenticated resource that displays historical spot rates for Bitcoin in USD. This is a paged resource and will return the first page by default.
 * @return One thousand historical spot prices representing page 1.
 * @throws IOException
 * @see <a
   *     href="https://coinbase.com/api/doc/1.0/prices/historical.html">coinbase.com/api/doc/1.0/prices/historical.html</a>
 */
public CoinbaseSpotPriceHistory getCoinbaseHistoricalSpotRates() throws IOException {
  return getCoinbaseHistoricalSpotRates(null);
}
