private RequestMatcher createSingleTextMatcher(final String name,final TextContainer container){
  if (container.isRawText()) {
    return createSingleMatcher(name,container.getText());
  }
  if (isExistOperator(container)) {
    return existMatcher(Extractors.extractor(name),container);
  }
  return createRequestMatcherWithResource(container.getOperation(),createResource(name,container.getText()));
}
