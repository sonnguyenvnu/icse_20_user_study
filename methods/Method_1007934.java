@Override public List<ImageHistory> history(final String image) throws DockerException, InterruptedException {
  final WebTarget resource=resource().path("images").path(image).path("history");
  try {
    return request(GET,IMAGE_HISTORY_LIST,resource,resource.request(APPLICATION_JSON_TYPE));
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
