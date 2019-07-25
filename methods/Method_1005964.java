@Override protected Void execute(KillContainerCmd command){
  WebTarget webResource=getBaseResource().path("/containers/{id}/kill").resolveTemplate("id",command.getContainerId());
  if (command.getSignal() != null) {
    webResource=webResource.queryParam("signal",command.getSignal());
  }
  LOGGER.trace("POST: {}",webResource);
  webResource.request().accept(MediaType.APPLICATION_JSON).post(null).close();
  return null;
}
