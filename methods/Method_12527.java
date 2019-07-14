public static InstanceExchangeFilterFunction timeout(Duration defaultTimeout,Map<String,Duration> timeoutPerEndpoint){
  return (instance,request,next) -> {
    Duration timeout=request.attribute(ATTRIBUTE_ENDPOINT).map(timeoutPerEndpoint::get).orElse(defaultTimeout);
    return next.exchange(request).timeout(timeout);
  }
;
}
