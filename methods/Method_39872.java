public static String getPathInfo(final HttpServletRequest request){
  String result=getIncludePathInfo(request);
  if (result == null) {
    result=request.getPathInfo();
  }
  return result;
}
