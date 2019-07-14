/** 
 * doParsePreview
 * @param request
 * @param response
 * @param parameters
 * @throws ServletException
 * @throws IOException
 * @throws DatabaseServiceException 
 */
private void doParsePreview(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException, DatabaseServiceException {
  if (logger.isDebugEnabled()) {
    logger.debug("JobID::{}",parameters.getProperty("jobID"));
  }
  long jobID=Long.parseLong(parameters.getProperty("jobID"));
  ImportingJob job=ImportingManager.getJob(jobID);
  if (job == null) {
    HttpUtilities.respond(response,"error","No such import job");
    return;
  }
  DatabaseQueryInfo databaseQueryInfo=getQueryInfo(request);
  if (databaseQueryInfo == null) {
    HttpUtilities.respond(response,"error","Invalid or missing Query Info");
  }
  job.updating=true;
  try {
    ObjectNode optionObj=ParsingUtilities.evaluateJsonStringToObjectNode(request.getParameter("options"));
    List<Exception> exceptions=new LinkedList<Exception>();
    job.prepareNewProject();
    parsePreview(databaseQueryInfo,job.project,job.metadata,job,DEFAULT_PREVIEW_LIMIT,optionObj,exceptions);
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
        writer.writeStringField("message",getExceptionString(exceptions));
      }
      writer.writeEndObject();
    }
 catch (    IOException e) {
      throw new ServletException(e);
    }
 finally {
      writer.flush();
      writer.close();
      w.flush();
      w.close();
    }
  }
 catch (  IOException e) {
    throw new ServletException(e);
  }
 finally {
    job.touch();
    job.updating=false;
  }
}
