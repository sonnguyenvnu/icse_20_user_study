private HttpResponseSetting bindToSession(final HttpServer server){
  if (isMount()) {
    return server.mount(mount.getDir(),to(mount.getUri()),mount.getMountPredicates()).response(mount.getResponseHandler());
  }
  if (isProxy()) {
    if (proxy.hasUrl()) {
      throw new IllegalArgumentException("It's not allowed to have URL in proxy from server");
    }
    return server.proxy(proxy.getProxyConfig(),proxy.getFailover());
  }
  if (isAnyResponse()) {
    return server.response(getResponseHandler());
  }
  HttpResponseSetting targetRequest=server.request(getRequestMatcher());
  if (isRedirectResponse()) {
    return targetRequest.redirectTo(this.redirectTo.asResource());
  }
  return targetRequest.response(getResponseHandler());
}
