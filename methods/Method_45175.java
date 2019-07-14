protected final void anySetting(final RequestMatcher anyMatcher,final ResponseHandler handler){
  if (handler != null) {
    this.response(handler);
    this.anyMatcher=anyMatcher;
  }
}
