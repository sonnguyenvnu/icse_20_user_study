/** 
 * Forwards a request to the existing framework container. This is called by the <code>AwsProxyRequestDispatcher</code> object
 * @param servletRequest The modified request object with the new request path
 * @param servletResponse The original servlet response
 * @throws ServletException
 * @throws IOException
 */
public void forward(ContainerRequestType servletRequest,ContainerResponseType servletResponse) throws ServletException, IOException {
  try {
    handleRequest(servletRequest,(ContainerResponseType)getServletResponse(servletResponse),lambdaContext);
  }
 catch (  Exception e) {
    log.error("Could not forward request",e);
    throw new ServletException(e);
  }
}
