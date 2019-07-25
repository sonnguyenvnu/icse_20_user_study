@Override public LogStream logs(final String containerId,final LogsParam... params) throws DockerException, InterruptedException {
  WebTarget resource=noTimeoutResource().path("containers").path(containerId).path("logs");
  for (  final LogsParam param : params) {
    resource=resource.queryParam(param.name(),param.value());
  }
  return getLogStream(GET,resource,containerId);
}
