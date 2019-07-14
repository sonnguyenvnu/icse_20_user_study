/** 
 * doCreateProject
 * @param request
 * @param response
 * @param parameters
 */
private void doCreateProject(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  if (logger.isDebugEnabled()) {
    logger.debug("DatabaseImportController::doCreateProject:::{}",parameters.getProperty("jobID"));
  }
  long jobID=Long.parseLong(parameters.getProperty("jobID"));
  final ImportingJob job=ImportingManager.getJob(jobID);
  if (job == null) {
    HttpUtilities.respond(response,"error","No such import job");
    return;
  }
  final DatabaseQueryInfo databaseQueryInfo=getQueryInfo(request);
  if (databaseQueryInfo == null) {
    HttpUtilities.respond(response,"error","Invalid or missing Query Info");
  }
  job.updating=true;
  try {
    final ObjectNode optionObj=ParsingUtilities.evaluateJsonStringToObjectNode(request.getParameter("options"));
    final List<Exception> exceptions=new LinkedList<Exception>();
    job.setState("creating-project");
    final Project project=new Project();
    new Thread(){
      @Override public void run(){
        ProjectMetadata pm=new ProjectMetadata();
        pm.setName(JSONUtilities.getString(optionObj,"projectName","Untitled"));
        pm.setEncoding(JSONUtilities.getString(optionObj,"encoding","UTF-8"));
        try {
          parseCreate(databaseQueryInfo,project,pm,job,-1,optionObj,exceptions);
        }
 catch (        DatabaseServiceException e) {
          logger.error("DatabaseImportController::doCreateProject:::run{}",e);
        }
        if (!job.canceled) {
          if (exceptions.size() > 0) {
            job.setError(exceptions);
          }
 else {
            project.update();
            ProjectManager.singleton.registerProject(project,pm);
            job.setState("created-project");
            job.setProjectID(project.id);
          }
          job.touch();
          job.updating=false;
        }
      }
    }
.start();
    HttpUtilities.respond(response,"ok","done");
  }
 catch (  IOException e) {
    throw new ServletException(e);
  }
}
