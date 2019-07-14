/** 
 * Removes instance map from the request.
 */
protected void removeRequestMap(final HttpServletRequest servletRequest){
  servletRequest.removeAttribute(ATTR_NAME);
}
