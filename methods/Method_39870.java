public static String getBaseServletPath(final HttpServletRequest request){
  String result=getForwardServletPath(request);
  if (result == null) {
    result=request.getServletPath();
  }
  return result;
}
