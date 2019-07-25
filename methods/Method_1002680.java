@Nullable final HttpHeadersBase getters(){
  if (delegate != null) {
    return delegate;
  }
  if (parent != null) {
    return parent;
  }
  return null;
}
