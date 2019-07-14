protected Function<Endpoints,Endpoints> alignWithManagementUrl(InstanceId instanceId,String managementUrl){
  return endpoints -> {
    if (!managementUrl.startsWith("https:")) {
      return endpoints;
    }
    if (endpoints.stream().noneMatch(e -> e.getUrl().startsWith("http:"))) {
      return endpoints;
    }
    log.warn("Endpoints for instance {} queried from {} are falsely using http. Rewritten to https. Consider configuring this instance to use 'server.use-forward-headers=true'.",instanceId,managementUrl);
    return Endpoints.of(endpoints.stream().map(e -> Endpoint.of(e.getId(),e.getUrl().replaceFirst("http:","https:"))).collect(Collectors.toList()));
  }
;
}
