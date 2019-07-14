private Function<Field,RequestMatcher> fieldToRequestMatcher(final RequestSetting request){
  return new Function<Field,RequestMatcher>(){
    @Override public RequestMatcher apply(    final Field field){
      try {
        Object value=field.get(request);
        return createRequestMatcherFromValue(field.getName(),value);
      }
 catch (      Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
;
}
