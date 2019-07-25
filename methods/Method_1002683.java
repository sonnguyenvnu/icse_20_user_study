@Override public final Set<AsciiString> names(){
  final HttpHeadersBase getters=getters();
  return getters != null ? getters.names() : ImmutableSet.of();
}
