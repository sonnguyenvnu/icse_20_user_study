@Deactivate protected void deactivate(){
  httpService.unregister(WEBAPP_ALIAS + "/" + SERVLET_NAME);
  httpService.unregister(WEBAPP_ALIAS);
  logger.info("Stopped Classic UI");
}
