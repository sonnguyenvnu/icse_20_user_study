private Mono<ClientResponse> forward(Instance instance,URI uri,HttpMethod method,HttpHeaders headers,Supplier<BodyInserter<?,? super ClientHttpRequest>> bodyInserter){
  WebClient.RequestBodySpec bodySpec=instanceWebClient.instance(instance).method(method).uri(uri).headers(h -> h.addAll(filterHeaders(headers)));
  WebClient.RequestHeadersSpec<?> headersSpec=bodySpec;
  if (requiresBody(method)) {
    try {
      headersSpec=bodySpec.body(bodyInserter.get());
    }
 catch (    Exception ex) {
      return Mono.error(ex);
    }
  }
  return headersSpec.exchange().onErrorResume(ReadTimeoutException.class,ex -> Mono.fromSupplier(() -> {
    log.trace("Timeout for Proxy-Request for instance {} with URL '{}'",instance.getId(),uri);
    return ClientResponse.create(HttpStatus.GATEWAY_TIMEOUT,strategies).build();
  }
)).onErrorResume(ResolveEndpointException.class,ex -> Mono.fromSupplier(() -> {
    log.trace("No Endpoint found for Proxy-Request for instance {} with URL '{}'",instance.getId(),uri);
    return ClientResponse.create(HttpStatus.NOT_FOUND,strategies).build();
  }
)).onErrorResume(IOException.class,ex -> Mono.fromSupplier(() -> {
    log.trace("Proxy-Request for instance {} with URL '{}' errored",instance.getId(),uri,ex);
    return ClientResponse.create(HttpStatus.BAD_GATEWAY,strategies).build();
  }
)).onErrorResume(ConnectException.class,ex -> Mono.fromSupplier(() -> {
    log.trace("Connect for Proxy-Request for instance {} with URL '{}' failed",instance.getId(),uri,ex);
    return ClientResponse.create(HttpStatus.BAD_GATEWAY,strategies).build();
  }
));
}
