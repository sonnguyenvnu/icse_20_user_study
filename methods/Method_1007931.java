@Override public InputStream save(final String... images) throws DockerException, IOException, InterruptedException {
  WebTarget resource;
  if (images.length == 1) {
    resource=resource().path("images").path(images[0]).path("get");
  }
 else {
    resource=resource().path("images").path("get");
    if (images.length > 1) {
      for (      final String image : images) {
        if (!isNullOrEmpty(image)) {
          resource=resource.queryParam("names",urlEncode(image));
        }
      }
    }
  }
  return request(GET,InputStream.class,resource,resource.request(APPLICATION_JSON_TYPE));
}
