public void run(){
  try {
    List<Tool> toolList=toolDAO.getAll();
    Boolean enabledMarathon=Boolean.valueOf(System.getenv(Constants.ENABLED_MARATHON));
    if (enabledMarathon) {
      containersHealthCheckWithMarathon(toolList);
    }
 else {
      containersHealthCheckWithNoMarathon(toolList);
    }
  }
 catch (  Exception e) {
    log.error("Tool health check error",e);
  }
catch (  Throwable th) {
    log.error("Tool health check error",th);
  }
}
