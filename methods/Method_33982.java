/** 
 * Get the callback URL for the specified request.
 * @param request The request.
 * @return The callback URL.
 */
protected String getCallbackURL(HttpServletRequest request){
  return new DefaultSavedRequest(request,getPortResolver()).getRedirectUrl();
}
