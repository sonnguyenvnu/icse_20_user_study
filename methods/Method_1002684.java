@Override public final Iterator<Entry<AsciiString,String>> iterator(){
  final HttpHeadersBase getters=getters();
  return getters != null ? getters.iterator() : Collections.emptyIterator();
}
