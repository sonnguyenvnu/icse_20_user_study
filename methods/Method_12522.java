public static InstanceExchangeFilterFunction rewriteEndpointUrl(){
  return (instance,request,next) -> {
    if (request.url().isAbsolute()) {
      log.trace("Absolute URL '{}' for instance {} not rewritten",request.url(),instance.getId());
      if (request.url().toString().equals(instance.getRegistration().getManagementUrl())) {
        request=ClientRequest.from(request).attribute(ATTRIBUTE_ENDPOINT,Endpoint.ACTUATOR_INDEX).build();
      }
      return next.exchange(request);
    }
    UriComponents requestUrl=UriComponentsBuilder.fromUri(request.url()).build();
    if (requestUrl.getPathSegments().isEmpty()) {
      return Mono.error(new ResolveEndpointException("No endpoint specified"));
    }
    String endpointId=requestUrl.getPathSegments().get(0);
    Optional<Endpoint> endpoint=instance.getEndpoints().get(endpointId);
    if (!endpoint.isPresent()) {
      return Mono.error(new ResolveEndpointException("Endpoint '" + endpointId + "' not found"));
    }
    URI rewrittenUrl=rewriteUrl(requestUrl,endpoint.get().getUrl());
    log.trace("URL '{}' for Endpoint {} of instance {} rewritten to {}",requestUrl,endpoint.get().getId(),instance.getId(),rewrittenUrl);
    request=ClientRequest.from(request).attribute(ATTRIBUTE_ENDPOINT,endpoint.get().getId()).url(rewrittenUrl).build();
    return next.exchange(request);
  }
;
}
