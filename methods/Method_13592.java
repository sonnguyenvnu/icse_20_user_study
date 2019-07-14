@Override public ClientHttpResponse intercept(HttpRequest httpRequest,byte[] bytes,ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
  HttpRequestWrapper requestWrapper=new HttpRequestWrapper(httpRequest);
  String xid=RootContext.getXID();
  if (!StringUtils.isEmpty(xid)) {
    requestWrapper.getHeaders().add(RootContext.KEY_XID,xid);
  }
  return clientHttpRequestExecution.execute(requestWrapper,bytes);
}
