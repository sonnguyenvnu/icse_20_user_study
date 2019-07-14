public static DefaultMutableHttpResponse newResponse(final HttpRequest request,final int status){
  DefaultMutableHttpResponse httpResponse=new DefaultMutableHttpResponse();
  httpResponse.version=request.getVersion();
  httpResponse.status=status;
  return httpResponse;
}
