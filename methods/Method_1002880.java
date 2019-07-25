/** 
 * Returns the server-side context of the  {@link Request} that is being handled in the current thread.
 * @throws IllegalStateException if the context is unavailable in the current thread orthe current context is not a  {@link ServiceRequestContext}.
 */
static ServiceRequestContext current(){
  final RequestContext ctx=RequestContext.current();
  checkState(ctx instanceof ServiceRequestContext,"The current context is not a server-side context: %s",ctx);
  return (ServiceRequestContext)ctx;
}
