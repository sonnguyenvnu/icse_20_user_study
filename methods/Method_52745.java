@Override protected AntlrTokenFilter getTokenFilter(final AntlrTokenManager tokenManager){
  return new KotlinTokenFilter(tokenManager);
}
