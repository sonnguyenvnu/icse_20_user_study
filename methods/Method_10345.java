@Override protected RequestHandle sendRequest(DefaultHttpClient client,HttpContext httpContext,HttpUriRequest uriRequest,String contentType,ResponseHandlerInterface responseHandler,Context context){
  if (contentType != null) {
    uriRequest.addHeader(AsyncHttpClient.HEADER_CONTENT_TYPE,contentType);
  }
  responseHandler.setUseSynchronousMode(true);
  newAsyncHttpRequest(client,httpContext,uriRequest,contentType,responseHandler,context).run();
  return new RequestHandle(null);
}
