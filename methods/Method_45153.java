@Override public final T response(final Resource resource){
  return this.response(with(checkNotNull(resource,"Resource should not be null")));
}
