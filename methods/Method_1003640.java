/** 
 * Constructs a handler using the given action to define a chain.
 * @param action The action that defines the all chain
 * @return A all representing the chain
 * @throws Exception any thrown by {@code action}
 */
default Handler chain(Action<? super Chain> action) throws Exception {
  return Handlers.chain(getServerConfig(),getRegistry(),action);
}
