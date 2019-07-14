/** 
 * Include named resource.
 */
public static boolean includeNamed(final ServletContext context,final ServletRequest request,final ServletResponse response,final String page) throws IOException, ServletException {
  RequestDispatcher dispatcher=context.getNamedDispatcher(page);
  if (dispatcher != null) {
    dispatcher.include(request,response);
    return true;
  }
  return false;
}
