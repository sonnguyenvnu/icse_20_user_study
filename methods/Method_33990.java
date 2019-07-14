/** 
 * Loads the access token dependencies for the given request. This will be a set of  {@link ProtectedResourceDetails#getId() resource ids}for which an OAuth access token is required.
 * @param request     The request.
 * @param response    The response
 * @param filterChain The filter chain
 * @return The access token dependencies (could be empty).
 */
protected Set<String> getAccessTokenDependencies(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain){
  Set<String> deps=new TreeSet<String>();
  if (getObjectDefinitionSource() != null) {
    FilterInvocation invocation=new FilterInvocation(request,response,filterChain);
    Collection<ConfigAttribute> attributes=getObjectDefinitionSource().getAttributes(invocation);
    if (attributes != null) {
      for (      ConfigAttribute attribute : attributes) {
        deps.add(attribute.getAttribute());
      }
    }
  }
  return deps;
}
