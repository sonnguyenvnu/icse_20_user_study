/** 
 * Forward to named resource.
 */
public static boolean forwardNamed(final ServletContext context,final ServletRequest request,final ServletResponse response,final String resource) throws IOException, ServletException {
  RequestDispatcher dispatcher=context.getNamedDispatcher(resource);
  if (dispatcher != null) {
    dispatcher.forward(request,response);
    return true;
  }
  return false;
}
