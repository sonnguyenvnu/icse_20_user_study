@Override protected Void execute(CopyArchiveToContainerCmd command){
  WebTarget webResource=getBaseResource().path("/containers/{id}/archive").resolveTemplate("id",command.getContainerId());
  LOGGER.trace("PUT: " + webResource.toString());
  InputStream streamToUpload=command.getTarInputStream();
  webResource.queryParam("path",command.getRemotePath()).queryParam("noOverwriteDirNonDir",command.isNoOverwriteDirNonDir()).request().put(entity(streamToUpload,"application/x-tar")).close();
  return null;
}
