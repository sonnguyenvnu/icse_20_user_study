private void prepareForKeepAlive(final FullHttpRequest request,final FullHttpResponse response){
  if (isKeepAlive(request)) {
    setKeepAlive(response,true);
    setContentLengthForKeepAlive(response);
  }
}
