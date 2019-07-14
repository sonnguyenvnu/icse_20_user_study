protected String getHealthEndpointPath(){
  String health=pathMappedEndpoints.getPath(EndpointId.of("health"));
  if (StringUtils.hasText(health)) {
    return health;
  }
  String status=pathMappedEndpoints.getPath(EndpointId.of("status"));
  if (StringUtils.hasText(status)) {
    return status;
  }
  throw new IllegalStateException("Either health or status endpoint must be enabled!");
}
