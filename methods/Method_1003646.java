/** 
 * Builds a chain, backed by the given registry.
 * @param registry The registry.
 * @param action The chain building action.
 * @return A handler
 * @throws Exception any thrown by {@code action}
 */
public static Handler chain(Registry registry,Action<? super Chain> action) throws Exception {
  return chain(registry.get(ServerConfig.class),registry,action);
}
