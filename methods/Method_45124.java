private HttpRequestBase createBaseRequest(final URL url,final HttpMethod method){
  if (method == HttpMethod.GET) {
    return new HttpGet(url.toString());
  }
  if (method == HttpMethod.POST) {
    return new HttpPost(url.toString());
  }
  if (method == HttpMethod.PUT) {
    return new HttpPut(url.toString());
  }
  if (method == HttpMethod.DELETE) {
    return new HttpDelete(url.toString());
  }
  if (method == HttpMethod.HEAD) {
    return new HttpHead(url.toString());
  }
  if (method == HttpMethod.OPTIONS) {
    return new HttpOptions(url.toString());
  }
  if (method == HttpMethod.TRACE) {
    return new HttpTrace(url.toString());
  }
  if (method == HttpMethod.PATCH) {
    return new HttpPatch(url.toString());
  }
  throw new MocoException("unknown HTTP method");
}
