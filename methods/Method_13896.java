private void doParsePreview(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  String token=TokenCookie.getToken(request);
  long jobID=Long.parseLong(parameters.getProperty("jobID"));
  ImportingJob job=ImportingManager.getJob(jobID);
  if (job == null) {
    HttpUtilities.respond(response,"error","No such import job");
    return;
  }
  job.updating=true;
  ObjectNode optionObj=ParsingUtilities.evaluateJsonStringToObjectNode(request.getParameter("options"));
  List<Exception> exceptions=new LinkedList<Exception>();
  job.prepareNewProject();
  GDataImporter.parse(token,job.project,job.metadata,job,100,optionObj,exceptions);
  Writer w=response.getWriter();
  JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
  try {
    writer.writeStartObject();
    if (exceptions.size() == 0) {
      job.project.update();
      writer.writeStringField("status","ok");
    }
 else {
      writer.writeStringField("status","error");
      writer.writeArrayFieldStart("errors");
      DefaultImportingController.writeErrors(writer,exceptions);
      writer.writeEndArray();
    }
    writer.writeEndObject();
  }
 catch (  IOException e) {
    throw new ServletException(e);
  }
 finally {
    writer.flush();
    writer.close();
    w.flush();
    w.close();
  }
  job.touch();
  job.updating=false;
}
