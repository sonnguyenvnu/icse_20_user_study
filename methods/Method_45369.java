private RequestMatcher createRequestMatcherFromValue(final String name,final Object value){
  if ("json".equalsIgnoreCase(name)) {
    return by(Moco.json(value));
  }
  if (Map.class.isInstance(value)) {
    return createCompositeMatcher(name,castToMap(value));
  }
  if (TextContainer.class.isInstance(value)) {
    return createSingleTextMatcher(name,TextContainer.class.cast(value));
  }
  throw new IllegalArgumentException("unknown configuration :" + value);
}
