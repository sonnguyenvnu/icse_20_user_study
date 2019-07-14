/** 
 * Forward to page path relative to the root of the ServletContext.
 */
public static boolean forwardAbsolute(final ServletContext context,final ServletRequest request,final ServletResponse response,final String resource) throws IOException, ServletException {
  RequestDispatcher dispatcher=context.getRequestDispatcher(resource);
  if (dispatcher != null) {
    dispatcher.forward(request,response);
    return true;
  }
  return false;
}
