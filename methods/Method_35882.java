public HttpHeaders plus(HttpHeader... additionalHeaders){
  return new HttpHeaders(ImmutableList.<HttpHeader>builder().addAll(all()).addAll(asList(additionalHeaders)).build());
}
