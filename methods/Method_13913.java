@Override public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
  String token=TokenCookie.getToken(request);
  if (token == null) {
    HttpUtilities.respond(response,"error","Not authorized");
    return;
  }
  ProjectManager.singleton.setBusy(true);
  try {
    Project project=getProject(request);
    Engine engine=getEngine(request,project);
    Properties params=ExportRowsCommand.getRequestParameters(request);
    String name=params.getProperty("name");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type","application/json");
    Writer w=response.getWriter();
    JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
    try {
      writer.writeStartObject();
      List<Exception> exceptions=new LinkedList<Exception>();
      String url=upload(project,engine,params,token,name,exceptions);
      if (url != null) {
        writer.writeStringField("status","ok");
        writer.writeStringField("url",url);
      }
 else       if (exceptions.size() == 0) {
        writer.writeStringField("status","error");
        writer.writeStringField("message","No such format");
      }
 else {
        for (        Exception e : exceptions) {
          logger.warn(e.getLocalizedMessage(),e);
        }
        writer.writeStringField("status","error");
        writer.writeStringField("message",exceptions.get(0).getLocalizedMessage());
      }
    }
 catch (    Exception e) {
      e.printStackTrace();
      writer.writeStringField("status","error");
      writer.writeStringField("message",e.getMessage());
    }
 finally {
      writer.writeEndObject();
      writer.flush();
      writer.close();
      w.flush();
      w.close();
    }
  }
 catch (  Exception e) {
    throw new ServletException(e);
  }
 finally {
    ProjectManager.singleton.setBusy(false);
  }
}
