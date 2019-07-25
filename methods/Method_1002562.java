@Override protected Promise<? extends IndividualRequest> run(Context context) throws Throwable {
  if (_individualRequest.hasHeaders() && _individualRequest.getHeaders().size() > 0) {
    for (    Map.Entry<String,String> headerEntry : _individualRequest.getHeaders().entrySet()) {
      String headerName=headerEntry.getKey();
      if (!_individualRequestHeaderWhitelist.contains(headerName)) {
        return Promises.error(new IndividualResponseException(HttpStatus.S_400_BAD_REQUEST,String.format("Request header %s is not allowed in the individual request.",headerName),_errorResponseBuilder));
      }
    }
  }
  return Promises.value(_individualRequest);
}
