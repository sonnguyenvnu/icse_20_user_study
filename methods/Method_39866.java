/** 
 * Returns <code>true</code> if request is a top-level one, i.e. previously not included or forwarded.
 */
public static boolean isTopLevelRequest(final HttpServletRequest request){
  return (getForwardRequestUri(request) == null) && (getIncludeRequestUri(request) == null);
}
