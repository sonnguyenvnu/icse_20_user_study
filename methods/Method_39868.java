/** 
 * Get current request uri.
 */
public static String getRequestUri(final HttpServletRequest request){
  String result=getIncludeRequestUri(request);
  if (result == null) {
    result=request.getRequestURI();
  }
  return result;
}
