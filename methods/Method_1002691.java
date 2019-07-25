@Override public String path(){
  final HttpHeadersBase getters=getters();
  checkState(getters != null,":path header does not exist.");
  return getters.path();
}
