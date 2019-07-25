/** 
 * Match the HttpContext and optionally provide a feature set to apply to a request.
 */
@Override public boolean matches(final QueryContext context){
  return context.httpContext().flatMap(httpContext -> httpContext.getClientId().map(clientId()::equals)).orElse(false);
}
