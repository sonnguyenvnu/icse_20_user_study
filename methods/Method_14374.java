private void doLoadRawData(HttpServletRequest request,HttpServletResponse response,Properties parameters) throws ServletException, IOException {
  long jobID=Long.parseLong(parameters.getProperty("jobID"));
  ImportingJob job=ImportingManager.getJob(jobID);
  if (job == null) {
    HttpUtilities.respond(response,"error","No such import job");
    return;
  }
  job.updating=true;
  ObjectNode config=job.getOrCreateDefaultConfig();
  if (!("new".equals(JSONUtilities.getString(config,"state",null)))) {
    HttpUtilities.respond(response,"error","Job already started; cannot load more data");
    return;
  }
  ImportingUtilities.loadDataAndPrepareJob(request,response,parameters,job,config);
  job.touch();
  job.updating=false;
}
