private void doUpdateFileSelection(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
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
  ArrayNode fileSelectionArray=ParsingUtilities.evaluateJsonStringToArrayNode(request.getParameter("fileSelection"));
  ImportingUtilities.updateJobWithNewFileSelection(job,fileSelectionArray);
  replyWithJobData(request,response,job);
  job.touch();
  job.updating=false;
}
