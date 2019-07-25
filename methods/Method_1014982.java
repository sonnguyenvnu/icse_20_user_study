@Override protected void service(HttpServletRequest servletRequest,HttpServletResponse servletResponse) throws ServletException, IOException {
  if (servletRequest.getAttribute(ATTR_TARGET_URI) == null) {
    servletRequest.setAttribute(ATTR_TARGET_URI,targetUri);
  }
  if (servletRequest.getAttribute(ATTR_TARGET_HOST) == null) {
    servletRequest.setAttribute(ATTR_TARGET_HOST,targetHost);
  }
  String method=servletRequest.getMethod();
  String proxyRequestUri=rewriteUrlFromRequest(servletRequest);
  HttpRequest proxyRequest;
  if (servletRequest.getHeader(HttpHeaders.CONTENT_LENGTH) != null || servletRequest.getHeader(HttpHeaders.TRANSFER_ENCODING) != null) {
    proxyRequest=newProxyRequestWithEntity(method,proxyRequestUri,servletRequest);
  }
 else {
    proxyRequest=new BasicHttpRequest(method,proxyRequestUri);
  }
  copyRequestHeaders(servletRequest,proxyRequest);
  setXForwardedForHeader(servletRequest,proxyRequest);
  HttpResponse proxyResponse=null;
  try {
    proxyResponse=doExecute(servletRequest,servletResponse,proxyRequest);
    int statusCode=proxyResponse.getStatusLine().getStatusCode();
    servletResponse.setStatus(statusCode,proxyResponse.getStatusLine().getReasonPhrase());
    copyResponseHeaders(proxyResponse,servletRequest,servletResponse);
    if (statusCode == HttpServletResponse.SC_NOT_MODIFIED) {
      servletResponse.setIntHeader(HttpHeaders.CONTENT_LENGTH,0);
    }
 else {
      copyResponseEntity(proxyResponse,servletResponse,proxyRequest,servletRequest);
    }
  }
 catch (  Exception e) {
    handleRequestException(proxyRequest,e);
  }
 finally {
    if (proxyResponse != null)     EntityUtils.consumeQuietly(proxyResponse.getEntity());
  }
}
