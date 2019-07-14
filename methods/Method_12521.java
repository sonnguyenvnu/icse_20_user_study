public static InstanceExchangeFilterFunction addHeaders(HttpHeadersProvider httpHeadersProvider){
  return (instance,request,next) -> {
    request=ClientRequest.from(request).headers(headers -> headers.addAll(httpHeadersProvider.getHeaders(instance))).build();
    return next.exchange(request);
  }
;
}
