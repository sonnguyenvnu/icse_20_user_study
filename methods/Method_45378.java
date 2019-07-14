private <T>RequestMatcher existMatcher(final RequestExtractor<T> extractor,final TextContainer container){
  String text=container.getText();
  if ("true".equalsIgnoreCase(text)) {
    return exist(extractor);
  }
  if ("false".equalsIgnoreCase(text)) {
    return not(exist(extractor));
  }
  throw new IllegalArgumentException(String.format("Unknown exist parameter: [%s]",text));
}
