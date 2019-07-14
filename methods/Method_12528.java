public static InstanceExchangeFilterFunction logfileAcceptWorkaround(){
  return (instance,request,next) -> {
    if (request.attribute(ATTRIBUTE_ENDPOINT).map(Endpoint.LOGFILE::equals).orElse(false)) {
      List<MediaType> newAcceptHeaders=Stream.concat(request.headers().getAccept().stream(),Stream.of(MediaType.ALL)).collect(Collectors.toList());
      request=ClientRequest.from(request).headers(h -> h.setAccept(newAcceptHeaders)).build();
    }
    return next.exchange(request);
  }
;
}
