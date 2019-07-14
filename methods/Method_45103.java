@Override protected final Optional<ImmutableMap<String,String>> doExtract(final HttpRequest request){
  Optional<String[]> cookieString=extractor.extract(request);
  if (!cookieString.isPresent()) {
    return absent();
  }
  return of(doExtractCookies(cookieString.get()));
}
