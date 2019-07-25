/** 
 * Creates a handler that implements authentication when the request path matches, and makes a Pac4j  {@link Clients} available to downstream handlers otherwise.<p> This methods performs the same function as  {@link #authenticator(String,ClientsProvider)}, but is more convenient to use when the  {@link Client} instances do not depend on the request environment.
 * @param path the path to bind the authenticator to (relative to the current request path binding)
 * @param clients the supported authentication clients
 * @return a handler
 */
public static Handler authenticator(String path,Client<?,?>... clients){
  ImmutableList<Client<?,?>> clientList=ImmutableList.copyOf(clients);
  return authenticator(path,ctx -> clientList);
}
