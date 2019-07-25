public BinaryData download(String resourceId){
  reset();
  return target.path(ResourcesServiceName.download.name()).queryParam("resourceId",resourceId).request().accept(MediaType.APPLICATION_JSON_TYPE).get(BinaryData.class);
}
