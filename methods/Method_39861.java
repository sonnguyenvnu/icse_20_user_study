/** 
 * Returns full URL: uri + query string, including the context path.
 */
public static String getFullUrl(final HttpServletRequest request){
  String url=request.getRequestURI();
  String query=request.getQueryString();
  if ((query != null) && (query.length() != 0)) {
    url+='?' + query;
  }
  return url;
}
