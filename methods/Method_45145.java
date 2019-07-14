@Override protected Optional<String> doRemoteUrl(final HttpRequest request){
  String uri=request.getUri();
  if (!proxyConfig.canAccessedBy(uri)) {
    return absent();
  }
  return of(proxyConfig.remoteUrl(uri));
}
