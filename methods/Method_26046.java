@Override protected Matcher<MethodTree> methodMatcher(){
  return allOf(anyOf(looksLikeJUnit3TearDown,looksLikeJUnit4After),not(hasJUnit4AfterAnnotations));
}
