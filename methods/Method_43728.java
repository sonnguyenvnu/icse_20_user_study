/** 
 * @deprecated Use @link {@link #placeCoinbaseProOrder} 
 */
public CoinbaseProIdResponse placeCoinbaseProLimitOrder(LimitOrder limitOrder) throws IOException {
  CoinbaseProPlaceLimitOrder coinbaseProLimitOrder=CoinbaseProAdapters.adaptCoinbaseProPlaceLimitOrder(limitOrder);
  return placeCoinbaseProOrder(coinbaseProLimitOrder);
}
