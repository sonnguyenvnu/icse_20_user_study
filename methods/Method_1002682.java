@Override public final boolean contains(CharSequence name,String value){
  final HttpHeadersBase getters=getters();
  return getters != null ? getters.contains(name,value) : false;
}
