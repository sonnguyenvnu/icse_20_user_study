public MultipartValuePatternBuilder withHeader(String name,StringValuePattern headerPattern){
  headerPatterns.put(name,MultiValuePattern.of(headerPattern));
  return this;
}
