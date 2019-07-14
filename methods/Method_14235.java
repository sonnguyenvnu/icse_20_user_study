@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  try {
    String name=request.getParameter("name");
    ProjectMetadata pm=getProjectMetadata(request);
    pm.setName(name);
    respond(response,"{ \"code\" : \"ok\" }");
  }
 catch (  Exception e) {
    respondException(response,e);
  }
}
