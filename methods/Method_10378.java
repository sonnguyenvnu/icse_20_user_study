@Override public AsyncHttpRequest getHttpRequest(DefaultHttpClient client,HttpContext httpContext,HttpUriRequest uriRequest,String contentType,ResponseHandlerInterface responseHandler,Context context){
  return new PrePostProcessRequest(client,httpContext,uriRequest,responseHandler);
}
