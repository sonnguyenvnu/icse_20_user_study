/** 
 * Returns <code>true</code> if target exists.
 */
protected boolean targetExists(final ActionRequest actionRequest,final String target){
  if (log.isDebugEnabled()) {
    log.debug("target check: " + target);
  }
  final ServletContext servletContext=actionRequest.getHttpServletRequest().getServletContext();
  try {
    return servletContext.getResource(target) != null;
  }
 catch (  MalformedURLException ignore) {
    return false;
  }
}
