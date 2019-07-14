@Override protected final Optional<String> doExtract(final HttpRequest request){
  return of(request.getVersion().text());
}
