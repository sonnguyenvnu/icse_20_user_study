@Override public HttpUriRequest getRedirect(HttpRequest request,HttpResponse response,HttpContext context) throws ProtocolException {
  URI uri=getLocationURI(request,response,context);
  String method=request.getRequestLine().getMethod();
  if ("post".equalsIgnoreCase(method)) {
    try {
      HttpRequestWrapper httpRequestWrapper=(HttpRequestWrapper)request;
      httpRequestWrapper.setURI(uri);
      httpRequestWrapper.removeHeaders("Content-Length");
      return httpRequestWrapper;
    }
 catch (    Exception e) {
      logger.error("???HttpRequestWrapper??");
    }
    return new HttpPost(uri);
  }
 else {
    return new HttpGet(uri);
  }
}
