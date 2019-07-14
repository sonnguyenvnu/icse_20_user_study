/** 
 * Creates instance map and stores it in the request.
 */
protected Map<String,TransientBeanData> createRequestMap(final HttpServletRequest servletRequest){
  Map<String,TransientBeanData> map=new HashMap<>();
  servletRequest.setAttribute(ATTR_NAME,map);
  return map;
}
