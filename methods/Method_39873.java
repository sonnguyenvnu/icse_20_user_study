public static String getQueryString(final HttpServletRequest request){
  String result=getIncludeQueryString(request);
  if (result == null) {
    result=request.getQueryString();
  }
  return result;
}
