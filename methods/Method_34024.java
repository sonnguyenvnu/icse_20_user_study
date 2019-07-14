/** 
 * Whether this filter is configured to process the specified request.
 * @param request     The request.
 * @param response    The response
 * @param filterChain The filter chain
 * @return Whether this filter is configured to process the specified request.
 */
protected boolean requiresAuthentication(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain){
  String uri=request.getRequestURI();
  int pathParamIndex=uri.indexOf(';');
  if (pathParamIndex > 0) {
    uri=uri.substring(0,pathParamIndex);
  }
  if ("".equals(request.getContextPath())) {
    return uri.endsWith(filterProcessesUrl);
  }
  boolean match=uri.endsWith(request.getContextPath() + filterProcessesUrl);
  if (log.isDebugEnabled()) {
    log.debug(uri + (match ? " matches " : " does not match ") + filterProcessesUrl);
  }
  return match;
}
