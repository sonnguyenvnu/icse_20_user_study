@Override public void push(final String image,final ProgressHandler handler,final RegistryAuth registryAuth) throws DockerException, InterruptedException {
  final ImageRef imageRef=new ImageRef(image);
  WebTarget resource=resource().path("images").path(imageRef.getImage()).path("push");
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
