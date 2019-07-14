public MultipartValuePatternBuilder withBody(ContentPattern<?> bodyPattern){
  bodyPatterns.add(bodyPattern);
  return this;
}
