private void doCreateProject(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  final String token=TokenCookie.getToken(request);
  long jobID=Long.parseLong(parameters.getProperty("jobID"));
  final ImportingJob job=ImportingManager.getJob(jobID);
  if (job == null) {
    HttpUtilities.respond(response,"error","No such import job");
    return;
  }
  job.updating=true;
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
        GDataImporter.parse(token,project,pm,job,-1,optionObj,exceptions);
      }
 catch (      IOException e) {
        logger.error(ExceptionUtils.getStackTrace(e));
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
