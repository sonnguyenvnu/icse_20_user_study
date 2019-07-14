@Override protected Matcher<MethodTree> methodMatcher(){
  return allOf(anyOf(looksLikeJUnit3SetUp,looksLikeJUnit4Before),not(hasJUnit4BeforeAnnotations));
}
