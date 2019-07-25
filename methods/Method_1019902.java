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
 catch (  Throwable th) {
    log.error("Error while doing auto scaling...",th);
  }
}
