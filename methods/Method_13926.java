@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  String username=request.getParameter("wb-username");
  String password=request.getParameter("wb-password");
  String remember=request.getParameter("remember-credentials");
  ConnectionManager manager=ConnectionManager.getInstance();
  if (username != null && password != null) {
    manager.login(username,password,"on".equals(remember));
  }
 else   if ("true".equals(request.getParameter("logout"))) {
    manager.logout();
  }
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Type","application/json");
  Writer w=response.getWriter();
  JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
  writer.writeStartObject();
  writer.writeBooleanField("logged_in",manager.isLoggedIn());
  writer.writeStringField("username",manager.getUsername());
  writer.writeEndObject();
  writer.flush();
  writer.close();
  w.flush();
  w.close();
}
