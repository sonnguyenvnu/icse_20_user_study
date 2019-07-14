@Override protected final Optional<String> doRemoteUrl(final HttpRequest request){
  try {
    URL targetUrl=url.apply(request);
    if (targetUrl != null) {
      return of(targetUrl.toString());
    }
    return absent();
  }
 catch (  IllegalArgumentException e) {
    return absent();
  }
}
