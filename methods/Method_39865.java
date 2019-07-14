/** 
 * Returns <code>true</code> if current page is included.
 */
public static boolean isPageIncluded(final HttpServletRequest request,final HttpServletResponse response){
  return (response.isCommitted() || (getIncludeServletPath(request) != null));
}
