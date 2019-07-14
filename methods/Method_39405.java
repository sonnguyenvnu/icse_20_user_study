/** 
 * Returns instance map from http request.
 */
@SuppressWarnings("unchecked") protected Map<String,TransientBeanData> getRequestMap(final HttpServletRequest servletRequest){
  return (Map<String,TransientBeanData>)servletRequest.getAttribute(ATTR_NAME);
}
