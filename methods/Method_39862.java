/** 
 * Returns url, without context path, convenient for request dispatcher.
 */
public static String getUrl(final HttpServletRequest request){
  String servletPath=request.getServletPath();
  String query=request.getQueryString();
  if ((query != null) && (query.length() != 0)) {
    servletPath+='?' + query;
  }
  return servletPath;
}
