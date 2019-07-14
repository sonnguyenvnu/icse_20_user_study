@Bean public InstanceExchangeFilterFunction auditLog(){
  return (instance,request,next) -> next.exchange(request).doOnSubscribe(s -> {
    if (HttpMethod.DELETE.equals(request.method()) || HttpMethod.POST.equals(request.method())) {
      log.info("{} for {} on {}",request.method(),instance.getId(),request.url());
    }
  }
);
}
