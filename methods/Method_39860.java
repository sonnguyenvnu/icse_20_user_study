/** 
 * Performs permanent redirection (301) to specified url.
 */
public static void redirectPermanent(final HttpServletRequest request,final HttpServletResponse response,String url){
  if (url.startsWith(StringPool.SLASH)) {
    url=ServletUtil.getContextPath(request) + url;
  }
  response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
  response.setHeader("Location",url);
}
