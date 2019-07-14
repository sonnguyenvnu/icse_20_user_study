private void doUpdateFormatAndOptions(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  long jobID=Long.parseLong(parameters.getProperty("jobID"));
  ImportingJob job=ImportingManager.getJob(jobID);
  if (job == null) {
    HttpUtilities.respond(response,"error","No such import job");
    return;
  }
  job.updating=true;
  ObjectNode config=job.getOrCreateDefaultConfig();
  if (!("ready".equals(JSONUtilities.getString(config,"state",null)))) {
    HttpUtilities.respond(response,"error","Job not ready");
    return;
  }
  String format=request.getParameter("format");
  ObjectNode optionObj=ParsingUtilities.evaluateJsonStringToObjectNode(request.getParameter("options"));
  List<Exception> exceptions=new LinkedList<Exception>();
  ImportingUtilities.previewParse(job,format,optionObj,exceptions);
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
      writeErrors(writer,exceptions);
      writer.writeEndArray();
    }
    writer.writeEndObject();
    writer.flush();
    writer.close();
  }
 catch (  IOException e) {
    throw new ServletException(e);
  }
 finally {
    w.flush();
    w.close();
  }
  job.touch();
  job.updating=false;
}
