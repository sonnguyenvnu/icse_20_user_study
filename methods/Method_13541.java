private RequestMetadata buildRequestMetadata(HttpServletRequest request,String restPath){
  UriComponents uriComponents=fromUriString(request.getRequestURI()).build(true);
  RequestMetadata requestMetadata=new RequestMetadata();
  requestMetadata.setPath(restPath);
  requestMetadata.setMethod(request.getMethod());
  requestMetadata.setParams(getParams(request));
  requestMetadata.setHeaders(getHeaders(request));
  return requestMetadata;
}
