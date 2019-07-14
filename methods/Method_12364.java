@EventListener public void onWebServerInitialized(WebServerInitializedEvent event){
  String name=event.getApplicationContext().getServerNamespace();
  if ("server".equals(name) || !StringUtils.hasText(name)) {
    localServerPort=event.getWebServer().getPort();
  }
 else   if ("management".equals(name)) {
    localManagementPort=event.getWebServer().getPort();
  }
}
