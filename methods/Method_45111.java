@Override protected Optional<String[]> doExtract(final HttpRequest request){
  String[] reference=request.getQueries().get(this.param);
  return fromNullable(reference);
}
