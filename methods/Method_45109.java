@Override protected Optional<String> doExtract(final HttpRequest request){
  return of(request.getMethod().name());
}
