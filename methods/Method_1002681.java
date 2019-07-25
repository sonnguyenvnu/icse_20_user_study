@Override public final boolean contains(CharSequence name){
  final HttpHeadersBase getters=getters();
  return getters != null ? getters.contains(name) : false;
}
