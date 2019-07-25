/** 
 * Creates a handler that implements authentication when the request path matches, and makes a Pac4j  {@link Clients} available to downstream handlers otherwise.<p> This handler <b>MUST</b> be <b>BEFORE</b> any code in the handler pipeline that tries to identify the user, such as a  {@link #requireAuth} handler in the pipeline.It should be added to the handler chain via the  {@link Chain#all(Handler)}. That is, it should not be added with  {@link Chain#get(Handler)} or any method that filters based on request method.It is common for this handler to be one of the first handlers in the pipeline. <p> This handler performs two different functions, based on whether the given path matches the  {@link PathBinding#getPastBinding()} component of the current path binding.If the path matches, the handler will attempt authentication, which may involve redirecting to an external auth provider, which may then redirect back to this handler. If authentication is successful, the  {@link UserProfile} of the authenticated user will be placed into the session.The user will then be redirected back to the URL that initiated the authentication. <p> If the path does not match, the handler will push an instance of  {@link Clients} into the context registry and pass control downstream.The  {@link Clients} instance will be retrieved downstream by any {@link #requireAuth(Class,Authorizer)} handler (or use of {@link #login(Context,Class)}.
 * @param path the path to bind the authenticator to (relative to the current request path binding)
 * @param clientsProvider the provider of authentication clients
 * @return a handler
 */
public static Handler authenticator(String path,ClientsProvider clientsProvider){
  return new Pac4jAuthenticator(path,clientsProvider);
}
