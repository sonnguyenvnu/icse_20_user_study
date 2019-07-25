@Override public CircuitBreakerHttpClient build(Client<HttpRequest,HttpResponse> delegate){
  if (needsContentInStrategy) {
    return new CircuitBreakerHttpClient(delegate,circuitBreakerMapping(),strategyWithContent());
  }
  return new CircuitBreakerHttpClient(delegate,circuitBreakerMapping(),strategy());
}
