/** 
 * Returns the base (top-level) uri.
 */
public static String getBaseRequestUri(final HttpServletRequest request){
  String result=getForwardRequestUri(request);
  if (result == null) {
    result=request.getRequestURI();
  }
  return result;
}
