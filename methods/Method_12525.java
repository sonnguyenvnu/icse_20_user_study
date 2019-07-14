public static InstanceExchangeFilterFunction setDefaultAcceptHeader(){
  return (instance,request,next) -> {
    if (request.headers().getAccept().isEmpty()) {
      Boolean isRequestForLogfile=request.attribute(ATTRIBUTE_ENDPOINT).map(Endpoint.LOGFILE::equals).orElse(false);
      List<MediaType> acceptedHeaders=isRequestForLogfile ? DEFAULT_LOGFILE_ACCEPT_MEDIATYPES : DEFAULT_ACCEPT_MEDIATYPES;
      request=ClientRequest.from(request).headers(headers -> headers.setAccept(acceptedHeaders)).build();
    }
    return next.exchange(request);
  }
;
}
