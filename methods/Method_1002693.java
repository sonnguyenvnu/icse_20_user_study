@Nullable @Override public String authority(){
  final HttpHeadersBase getters=getters();
  return getters != null ? getters.authority() : null;
}
