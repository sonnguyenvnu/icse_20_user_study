/** 
 * Given a request and response objects, triggers the filters set in the servlet context and
 * @param request The incoming request
 * @param response The response object Spring should write to.
 * @throws ServletException When an error occurs during processing or of the request
 * @throws IOException When an error occurs while writing the response
 */
public void dispatch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  currentResponse=response;
  dispatcherServlet.service(request,response);
}
