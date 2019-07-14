/** 
 * Resolves decorator path based on request and action path. If decorator is not found, returns <code>null</code>. By default applies decorator on all *.html pages.
 */
public String resolveDecorator(final HttpServletRequest request,final String actionPath){
  if (actionPath.endsWith(".html")) {
    return DEFAULT_DECORATOR;
  }
  return null;
}
