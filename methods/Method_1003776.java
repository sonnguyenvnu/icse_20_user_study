/** 
 * Builds a chain, backed by the given registry.
 * @param registry The registry.
 * @param closure The chain building closure.
 * @return A handler
 * @throws Exception any exception thrown by the given closure
 */
public static Handler chain(Registry registry,@DelegatesTo(value=GroovyChain.class,strategy=Closure.DELEGATE_FIRST) Closure<?> closure) throws Exception {
  return chain(registry.get(ServerConfig.class),registry,closure);
}
