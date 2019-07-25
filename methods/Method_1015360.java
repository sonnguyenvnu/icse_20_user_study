public static ExchangeFilterFunction retry(int defaultRetries,Map<String,Integer> retriesPerEndpoint){
  return (request,next) -> {
    int retries=0;
    if (!request.method().equals(HttpMethod.DELETE) && !request.method().equals(HttpMethod.PATCH) && !request.method().equals(HttpMethod.POST) && !request.method().equals(HttpMethod.PUT)) {
      retries=request.attribute(ATTRIBUTE_ENDPOINT).map(retriesPerEndpoint::get).orElse(defaultRetries);
    }
    return next.exchange(request).retry(retries);
  }
;
}
