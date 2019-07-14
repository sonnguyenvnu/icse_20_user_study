private void doInitializeParserUI(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  long jobID=Long.parseLong(parameters.getProperty("jobID"));
  ImportingJob job=ImportingManager.getJob(jobID);
  if (job == null) {
    HttpUtilities.respond(response,"error","No such import job");
    return;
  }
  String format=request.getParameter("format");
  Format formatRecord=ImportingManager.formatToRecord.get(format);
  if (formatRecord != null && formatRecord.parser != null) {
    ObjectNode options=formatRecord.parser.createParserUIInitializationData(job,job.getSelectedFileRecords(),format);
    ObjectNode result=ParsingUtilities.mapper.createObjectNode();
    JSONUtilities.safePut(result,"status","ok");
    JSONUtilities.safePut(result,"options",options);
    HttpUtilities.respond(response,result.toString());
  }
 else {
    HttpUtilities.respond(response,"error","Unrecognized format or format has no parser");
  }
}
