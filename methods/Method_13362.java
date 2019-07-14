private RequestMetadata buildRequestMetadata(HttpRequest request){
  UriComponents uriComponents=fromUri(request.getURI()).build(true);
  RequestMetadata requestMetadata=new RequestMetadata();
  requestMetadata.setPath(uriComponents.getPath());
  requestMetadata.setMethod(request.getMethod().name());
  requestMetadata.setParams(uriComponents.getQueryParams());
  requestMetadata.setHeaders(request.getHeaders());
  return requestMetadata;
}
