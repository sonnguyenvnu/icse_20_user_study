@Override protected final void doWriteToResponse(final HttpRequest httpRequest,final MutableHttpResponse httpResponse){
  Optional<URL> url=remoteUrl(httpRequest);
  if (!url.isPresent()) {
    return;
  }
  HttpResponse response=doProxy(httpRequest,url.get());
  doWritHttpResponse(response,httpResponse);
}
