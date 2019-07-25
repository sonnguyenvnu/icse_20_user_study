@Override public String ping() throws DockerException, InterruptedException {
  final WebTarget resource=client.target(uri).path("_ping");
  return request(GET,String.class,resource,resource.request());
}
