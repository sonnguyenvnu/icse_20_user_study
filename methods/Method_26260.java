@Override public Matcher<? super ExpressionTree> specializedMatcher(){
  return anyOf(RETURNS_SAME_TYPE,ReturnValueIgnored::functionalMethod,STREAM_METHOD,ARRAYS_METHODS,ReturnValueIgnored::javaTimeTypes);
}
