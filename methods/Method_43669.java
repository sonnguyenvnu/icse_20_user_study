/** 
 * Unauthenticated resource that displays historical spot rates for Bitcoin in USD.
 * @param page Optional parameter to request a desired page of results. Will return page 1 if thesupplied page is null or less than 1.
 * @return One thousand historical spot prices for the given page.
 * @throws IOException
 */
public CoinbaseSpotPriceHistory getCoinbaseHistoricalSpotRates(Integer page) throws IOException {
  return CoinbaseSpotPriceHistory.fromRawString(coinbase.getHistoricalSpotRates(page));
}
