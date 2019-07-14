/** 
 * Whether to skip processing for the specified request.
 * @param request The request.
 * @return Whether to skip processing.
 */
protected boolean skipProcessing(HttpServletRequest request){
  return ((request.getAttribute(OAUTH_PROCESSING_HANDLED) != null) && (Boolean.TRUE.equals(request.getAttribute(OAUTH_PROCESSING_HANDLED))));
}
