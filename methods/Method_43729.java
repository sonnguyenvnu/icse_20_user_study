/** 
 * @deprecated Use {@link #placeCoinbaseProOrder} 
 */
public CoinbaseProIdResponse placeCoinbaseProStopOrder(StopOrder stopOrder) throws IOException {
  CoinbaseProPlaceOrder coinbaseProStopOrder=CoinbaseProAdapters.adaptCoinbaseProStopOrder(stopOrder);
  return placeCoinbaseProOrder(coinbaseProStopOrder);
}
