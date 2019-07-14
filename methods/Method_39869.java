public static String getBaseContextPath(final HttpServletRequest request){
  String result=getForwardContextPath(request);
  if (result == null) {
    result=ServletUtil.getContextPath(request);
  }
  return result;
}
