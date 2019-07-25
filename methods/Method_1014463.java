@Deactivate protected void deactivate(){
  httpService.unregister(WEBAPP_ALIAS + "/" + SERVLET_NAME);
}
