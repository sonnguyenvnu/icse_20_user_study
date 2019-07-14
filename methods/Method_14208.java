@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  if (request == null) {
    throw new IllegalArgumentException("parameter 'request' should not be null");
  }
  if (response == null) {
    throw new IllegalArgumentException("parameter 'request' should not be null");
  }
  try {
    Project project=getProject(request);
    project.getProcessManager().cancelAll();
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    response.getWriter().write("{ \"code\" : \"ok\" }");
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
