/** 
 * Use instead of  {@link #placeLimitOrder(LimitOrder)} to bypass rate limitations and transienterrors when building up a simulated order book.
 * @param limitOrder The limit order.
 * @return The id of the resulting order.
 */
public String placeLimitOrderUnrestricted(LimitOrder limitOrder){
  MatchingEngine engine=exchange.getEngine(limitOrder.getCurrencyPair());
  return engine.postOrder(getApiKey(),limitOrder).getId();
}
