static public int getIntegerParameter(HttpServletRequest request,String name,int def){
  if (request == null) {
    throw new IllegalArgumentException("parameter 'request' should not be null");
  }
  try {
    return Integer.parseInt(request.getParameter(name));
  }
 catch (  Exception e) {
    logger.warn("Error getting integer parameter",e);
  }
  return def;
}
