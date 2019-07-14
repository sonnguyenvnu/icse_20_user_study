/** 
 * Handle incoming GETs
 */
@Override protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  if (isDestroyed) {
    response.sendError(503,"Service has been shut down.");
  }
 else {
    handleRequest(request,response);
  }
}
