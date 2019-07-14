private Function<RequestMatcher,RequestMatcher> applyConfig(final MocoConfig config){
  return new Function<RequestMatcher,RequestMatcher>(){
    @Override public RequestMatcher apply(    final RequestMatcher matcher){
      return matcher.apply(config);
    }
  }
;
}
