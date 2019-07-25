/** 
 * Sets the root handler to the chain specified by the given action. <p> The server registry is available during the action via the  {@link ratpack.handling.Chain#getRegistry()} method of the given chain.
 * @param handlers an action defining a handler chain
 * @return {@code this}
 * @see Chain
 */
default RatpackServerSpec handlers(Action<? super Chain> handlers){
  return handler(r -> Handlers.chain(r,handlers));
}
