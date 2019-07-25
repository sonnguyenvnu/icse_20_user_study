@Override protected Void execute(CopyArchiveToContainerCmd command){
  WebTarget webResource=getBaseResource().path("/containers/{id}/archive").resolveTemplate("id",command.getContainerId());
  LOGGER.trace("PUT: " + webResource.toString());
  InputStream streamToUpload=command.getTarInputStream();
  webResource.queryParam("path",command.getRemotePath()).queryParam("noOverwriteDirNonDir",command.isNoOverwriteDirNonDir()).request().put(streamToUpload,MediaType.APPLICATION_X_TAR);
  return null;
}
