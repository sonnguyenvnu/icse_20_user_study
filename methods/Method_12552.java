public Flux<InstanceResponse> forward(Flux<Instance> instances,URI uri,HttpMethod method,HttpHeaders headers,BodyInserter<?,? super ClientHttpRequest> bodyInserter){
  return instances.flatMap(instance -> this.forward(instance,uri,method,headers,bodyInserter).map(clientResponse -> Tuples.of(instance.getId(),clientResponse))).flatMap(t -> {
    ClientResponse clientResponse=t.getT2();
    InstanceResponse.Builder response=InstanceResponse.builder().instanceId(t.getT1()).status(clientResponse.rawStatusCode()).contentType(String.join(", ",clientResponse.headers().header(HttpHeaders.CONTENT_TYPE)));
    return clientResponse.bodyToMono(String.class).map(response::body).defaultIfEmpty(response).map(InstanceResponse.Builder::build);
  }
);
}
