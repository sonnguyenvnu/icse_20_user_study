public static String getBasePathInfo(final HttpServletRequest request){
  String result=getForwardPathInfo(request);
  if (result == null) {
    result=request.getPathInfo();
  }
  return result;
}
