/** 
 * Invoked when there is an error thrown in any part of doRequest() processing. <br> <br> Default will send a simple HTML response indicating there was a problem.
 * @param dumperOptions
 * @param context
 * @param report
 * @param request original HttpServletRequest from servlet container.
 * @param response HttpServletResponse object from servlet container.
 * @param cause Exception that was thrown by some other part of process.
 */
protected void error(IXDocReport report,IContext context,DumperOptions dumperOptions,HttpServletRequest request,HttpServletResponse response,Exception cause) throws ServletException, IOException {
  if (response.isCommitted()) {
    throw new ServletException(cause);
  }
  response.setContentType(TEXT_HTML_CONTENT_TYPE);
  StringBuilder html=new StringBuilder();
  html.append("<html>");
  html.append("<title>Error</title>");
  html.append("<body bgcolor=\"#ffffff\">");
  html.append("<h2>XDocReport Servlet: Error report generation</h2>");
  html.append("<pre>");
  String why=cause.getMessage();
  if (why != null && why.trim().length() > 0) {
    html.append(why);
    html.append("<br>");
  }
  StringWriter sw=new StringWriter();
  cause.printStackTrace(new PrintWriter(sw));
  html.append(sw.toString());
  html.append("</pre>");
  html.append("</body>");
  html.append("</html>");
  response.getOutputStream().print(html.toString());
}
