/** 
 * Set web port from  {@link ServiceBean#getExportedUrls() exported URLs}  if "rest" protocol is present.
 */
private void setServerPort(){
  if (serverPort == null) {
    for (    List<URL> urls : repository.getAllExportedUrls().values()) {
      urls.stream().filter(url -> REST_PROTOCOL.equalsIgnoreCase(url.getProtocol())).findFirst().ifPresent(url -> {
        serverPort=url.getPort();
      }
);
      if (serverPort == null) {
        urls.stream().findAny().ifPresent(url -> {
          serverPort=url.getPort();
        }
);
      }
    }
  }
}
