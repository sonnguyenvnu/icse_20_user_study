/** 
 * Returns the client-side context of the  {@link Request} that is being handled in the current thread.
 * @throws IllegalStateException if the context is unavailable in the current thread orthe current context is not a  {@link ClientRequestContext}.
 */
static ClientRequestContext current(){
  final RequestContext ctx=RequestContext.current();
  checkState(ctx instanceof ClientRequestContext,"The current context is not a client-side context: %s",ctx);
  return (ClientRequestContext)ctx;
}
