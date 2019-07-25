@Override protected Void execute(PingCmd command){
  WebTarget webResource=getBaseResource().path("/_ping");
  LOGGER.trace("GET: {}",webResource);
  webResource.request().get().close();
  return null;
}
