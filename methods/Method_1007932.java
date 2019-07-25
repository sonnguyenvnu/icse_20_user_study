@Override public void pull(final String image,final RegistryAuth registryAuth,final ProgressHandler handler) throws DockerException, InterruptedException {
  final ImageRef imageRef=new ImageRef(image);
  WebTarget resource=resource().path("images").path("create");
  resource=resource.queryParam("fromImage",imageRef.getImage());
  if (imageRef.getTag() != null) {
    resource=resource.queryParam("tag",imageRef.getTag());
  }
  try {
    requestAndTail(POST,handler,resource,resource.request(APPLICATION_JSON_TYPE).header("X-Registry-Auth",authHeader(registryAuth)));
  }
 catch (  DockerRequestException e) {
switch (e.status()) {
case 404:
      throw new ImageNotFoundException(image,e);
default :
    throw e;
}
}
}
