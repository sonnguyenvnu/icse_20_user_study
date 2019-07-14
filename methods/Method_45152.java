@Override public final T response(final String content){
  return this.response(text(checkNotNullOrEmpty(content,"Content should not be null")));
}
